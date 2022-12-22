package com.ute.ecwebapp.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.dto.ItemAuctionDto;
import com.ute.ecwebapp.dto.UserDto;
import com.ute.ecwebapp.entity.GenreEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.PhotoEntity;
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.ItemAuctionRepository;
import com.ute.ecwebapp.service.GenreService;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.PhotoService;
import com.ute.ecwebapp.service.UserService;
import com.ute.ecwebapp.util.ConvertList;
import com.ute.ecwebapp.util.DtoMapper;

@Service
public class ItemAuctionServiceImpl implements ItemAuctionService {

	@Autowired
	private ItemAuctionRepository itemAuctionRepository;

	@Autowired
	private DtoMapper dtoMapper;

	@Autowired
	private GenreService genreService;

	@Autowired
	private UserService userService;

	@Autowired
	private PhotoService photoService;

	@Autowired
	private ConvertList convertListPhoto;

	@Override
	public void createItemAuction(String json, MultipartFile multipartFile)
			throws IllegalStateException, JsonMappingException, JsonProcessingException, IOException {
		var itemAuctionEntity = new ItemAuctionEntity();
		var itemAuctionDto = dtoMapper.covertToItemAuctionDto(json);
		var genreEntity = new GenreEntity();
		var sellerEntity = new UserEntity();
		BeanUtils.copyProperties(itemAuctionDto, itemAuctionEntity);
		var genreId = dtoMapper.convertToInteger(json, "genreId");
		var sellerId = dtoMapper.convertToInteger(json, "sellerId");
		BeanUtils.copyProperties(genreService.getGenreById(genreId), genreEntity);
		BeanUtils.copyProperties(userService.getUserById(sellerId), sellerEntity);
		itemAuctionEntity.setGenre(genreEntity);
		itemAuctionEntity.setSeller(sellerEntity);
		if (multipartFile.getContentType().equals(MediaType.IMAGE_GIF_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_PNG_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
			String imageSource = String.valueOf(System.currentTimeMillis()) + multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			var photoEntity = new PhotoEntity();
			photoEntity.setPhotoName(imageSource);
			photoEntity.setPhotoData(multipartFile.getBytes());
			photoEntity.setMime(multipartFile.getContentType());
			photoEntity.setItemAuctionEntity(itemAuctionEntity);
			photoService.savePhoto(photoEntity);
			itemAuctionEntity.setPhotos(new HashSet<>(Arrays.asList(photoEntity)));
			itemAuctionEntity.setStatus(true);
			itemAuctionRepository.save(itemAuctionEntity);

		} else {
			throw new BadRequestException("Inappropriate File Type or Format");
		}
	}

	@Override
	public List<ItemAuctionDto> getAllItemAuctions() {
		List<ItemAuctionDto> listItemAuction = itemAuctionRepository.findAll().stream()
				.map(itemAucion -> extracted(itemAucion)).collect(Collectors.toList());
		return listItemAuction.stream().filter(itemAuction -> itemAuction.getStatus() == true)
				.collect(Collectors.toList());
	}

	private ItemAuctionDto extracted(ItemAuctionEntity itemAucion) {
		return new ItemAuctionDto(itemAucion.getItemAuctionId(), itemAucion.getDescription(), itemAucion.getTitle(),
				convertListPhoto.convertToPhotoDto(new ArrayList<>(itemAucion.getPhotos())),
				itemAucion.getStartBidAmount(), itemAucion.getAutoAcceptAmount(), itemAucion.getIncrement(),
				itemAucion.getStartDate(), itemAucion.getEndDate(), itemAucion.getStatus(),
				new UserDto(itemAucion.getSeller().getUserId(), itemAucion.getSeller().getEmail(),
						itemAucion.getSeller().getPhone(), itemAucion.getSeller().getFullName()),
				new GenreDto(itemAucion.getGenre().getGenreId(), itemAucion.getGenre().getGenreName()));
	}

	@Override
	public ItemAuctionDto getItemAuctionById(Integer itemAuctionId) {
		var itemAuctionEntity = itemAuctionRepository.findById(itemAuctionId).orElseThrow(() -> new BadRequestException(
				"Could not found the item auction with item auction id: " + itemAuctionId + "."));
		var itemAuctionDto = new ItemAuctionDto();
		BeanUtils.copyProperties(itemAuctionEntity, itemAuctionDto);
		List<PhotoEntity> listPhotoEntity = new ArrayList<>(itemAuctionEntity.getPhotos());
		itemAuctionDto.setPhotos(convertListPhoto.convertToListPhotoDto(listPhotoEntity));
		return itemAuctionDto;
	}

	@Override
	public void updateItemAuction(String json, Integer itemAuctionId, MultipartFile multipartFile) throws IOException {
		var itemAuctionDto = dtoMapper.covertToItemAuctionDto(json);
		var itemAuctionEntity = itemAuctionRepository.findById(itemAuctionId).orElseThrow(() -> new BadRequestException(
				"Could not found the item auction with item auction id: " + itemAuctionId + "."));
		var genreEntity = new GenreEntity();
		var sellerEntity = new UserEntity();
		BeanUtils.copyProperties(itemAuctionDto, itemAuctionEntity);
		var genreId = dtoMapper.convertToInteger(json, "genreId");
		var sellerId = dtoMapper.convertToInteger(json, "sellerId");
		BeanUtils.copyProperties(genreService.getGenreById(genreId), genreEntity);
		BeanUtils.copyProperties(userService.getUserById(sellerId), sellerEntity);
		itemAuctionEntity.setGenre(genreEntity);
		itemAuctionEntity.setSeller(sellerEntity);
		itemAuctionEntity.setDescription(itemAuctionDto.getDescription());
		Set<PhotoEntity> setPhotoEntity = new HashSet<>(
				convertListPhoto.convertToListPhotoEntity(itemAuctionDto.getPhotos(), itemAuctionEntity));
		itemAuctionEntity.setPhotos(setPhotoEntity);
		itemAuctionEntity.setStartBidAmount(itemAuctionDto.getStartBidAmount());
		itemAuctionEntity.setAutoAcceptAmount(itemAuctionDto.getAutoAcceptAmount());
		itemAuctionEntity.setIncrement(itemAuctionDto.getIncrement());
		itemAuctionEntity.setStartDate(itemAuctionDto.getStartDate());
		itemAuctionEntity.setEndDate(itemAuctionDto.getEndDate());
		if (multipartFile.getContentType().equals(MediaType.IMAGE_GIF_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_PNG_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
			String imageSource = String.valueOf(System.currentTimeMillis()) + multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			var photoEntity = photoService.getById(itemAuctionId);
			photoEntity.setPhotoName(imageSource);
			photoEntity.setPhotoData(multipartFile.getBytes());
			photoEntity.setMime(multipartFile.getContentType());
			photoService.updatePhoto(photoEntity);
			itemAuctionRepository.save(itemAuctionEntity);

		} else {
			throw new BadRequestException("Inappropriate File Type or Format");
		}
	}

	@Override
	public void deleteItemAuction(Integer itemAuctionId) {
		if (!itemAuctionRepository.existsById(itemAuctionId)) {
			throw new BadRequestException(
					"Could not found the item auction with item auction id: " + itemAuctionId + ".");
		}
		itemAuctionRepository.deleteById(itemAuctionId);
	}

	@Override
	public List<ItemAuctionDto> getAllGenreTitle(String title) {
		List<ItemAuctionDto> listItemAuction = itemAuctionRepository.findAllBytitle(title).stream()
				.map(itemAucion -> extracted(itemAucion)).collect(Collectors.toList());
		return listItemAuction.stream().filter(itemAuction -> itemAuction.getStatus() == true)
				.collect(Collectors.toList());
	}

	@Override
	public void updateItemAuction(MultipartFile multipartFile, Integer itemAuctionId) throws IOException {
		if (multipartFile.getContentType().equals(MediaType.IMAGE_GIF_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_PNG_VALUE)
				|| multipartFile.getContentType().equals(MediaType.IMAGE_JPEG_VALUE)) {
			String imageSource = String.valueOf(System.currentTimeMillis()) + multipartFile.getOriginalFilename()
					.substring(multipartFile.getOriginalFilename().lastIndexOf("."));
			var photoEntity = new PhotoEntity();
			photoEntity.setPhotoName(imageSource);
			photoEntity.setPhotoData(multipartFile.getBytes());
			photoEntity.setMime(multipartFile.getContentType());
			var itemAuctionEntity = itemAuctionRepository.findById(itemAuctionId)
					.orElseThrow(() -> new BadRequestException(
							"Could not found the item auction with item auction id: " + itemAuctionId + "."));
			photoEntity.setItemAuctionEntity(itemAuctionEntity);
			photoEntity.setItemAuctionEntity(itemAuctionEntity);
			photoService.updatePhoto(photoEntity);
		}
	}

	@Override
	public void createItemAuction(ItemAuctionEntity itemAuctionEntity) {
		itemAuctionRepository.save(itemAuctionEntity);
	}

	@Override
	public void updateItemAuctionId(ItemAuctionDto itemAuctionDto, Integer genreId) {
		var itemAuctionEntity = new ItemAuctionEntity();
		BeanUtils.copyProperties(itemAuctionDto, itemAuctionEntity);
		var genreDto = genreService.getGenreById(genreId);
		var genreEntity = new GenreEntity();
		BeanUtils.copyProperties(genreDto, genreEntity);
		itemAuctionEntity.setGenre(genreEntity);
		itemAuctionRepository.save(itemAuctionEntity);
	}

	@Override
	public List<ItemAuctionEntity> getAllItemAuctionsByStatus() {
		return itemAuctionRepository.findAll().stream().filter(itemAuction -> itemAuction.getStatus() == true)
				.collect(Collectors.toList());
	}
}
