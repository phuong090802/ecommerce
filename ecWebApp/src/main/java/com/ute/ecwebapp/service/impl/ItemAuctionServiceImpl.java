package com.ute.ecwebapp.service.impl;

import java.io.IOException;
import java.util.List;
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
import com.ute.ecwebapp.entity.UserEntity;
import com.ute.ecwebapp.exception.ItemActionNotFoundException;
import com.ute.ecwebapp.repository.ItemAuctionRepository;
import com.ute.ecwebapp.service.GenreService;
import com.ute.ecwebapp.service.ItemAuctionService;
import com.ute.ecwebapp.service.UserService;
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

	@Override
	public String createItemAuction(String json, MultipartFile multipartFile)
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
			itemAuctionEntity.setPhoto(imageSource);
			itemAuctionEntity.setPhotoData(multipartFile.getBytes());
			itemAuctionEntity.setType(multipartFile.getContentType());
			itemAuctionRepository.save(itemAuctionEntity);
			return json;
		}
		return "Inappropriate File Type or Format";
	}

	@Override
	public ItemAuctionEntity findByphoto(String photoName) {
		return itemAuctionRepository.findByphoto(photoName);
	}

	@Override
	public List<ItemAuctionDto> getAllItemAuctions() {
		return itemAuctionRepository.findAll().stream().map(itemAucion -> extracted(itemAucion))
				.collect(Collectors.toList());
	}

	private ItemAuctionDto extracted(ItemAuctionEntity itemAucion) {
		return new ItemAuctionDto(itemAucion.getItemAuctionId(), itemAucion.getDescription(), itemAucion.getTitle(),
				itemAucion.getPhoto(), itemAucion.getStartBidAmount(), itemAucion.getAutoAcceptAmount(),
				itemAucion.getIncrement(), itemAucion.getStartDate(), itemAucion.getEndDate(),
				new UserDto(itemAucion.getSeller().getUserId(), itemAucion.getSeller().getEmail(),
						itemAucion.getSeller().getPhone(), itemAucion.getSeller().getUserName()),
				new GenreDto(itemAucion.getGenre().getGenreId(), itemAucion.getGenre().getGenreName()));
	}

	@Override
	public ItemAuctionDto getItemAuctionById(Integer itemAuctionId) {
		var itemAuctionEntity = itemAuctionRepository.findById(itemAuctionId)
				.orElseThrow(() -> new ItemActionNotFoundException(itemAuctionId));
		var itemAuctionDto = new ItemAuctionDto();
		BeanUtils.copyProperties(itemAuctionEntity, itemAuctionDto);
		return itemAuctionDto;
	}

	@Override
	public String updateItemAuction(String json, Integer itemAuctionId, MultipartFile multipartFile)
			throws IOException {
		var itemAuctionDto = dtoMapper.covertToItemAuctionDto(json);
		var itemAuctionEntity = itemAuctionRepository.findById(itemAuctionId)
				.orElseThrow(() -> new ItemActionNotFoundException(itemAuctionId));
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
		itemAuctionEntity.setTitle(itemAuctionDto.getTitle());
		itemAuctionEntity.setPhoto(itemAuctionDto.getPhoto());
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
			itemAuctionEntity.setPhoto(imageSource);
			itemAuctionEntity.setPhotoData(multipartFile.getBytes());
			itemAuctionEntity.setType(multipartFile.getContentType());
			itemAuctionRepository.save(itemAuctionEntity);
			return json;

		}
		return "Inappropriate File Type or Format";
	}

	@Override
	public String deleteItemAuction(Integer itemAuctionId) {
		if (!itemAuctionRepository.existsById(itemAuctionId)) {
			throw new ItemActionNotFoundException(itemAuctionId);
		}
		itemAuctionRepository.deleteById(itemAuctionId);
		return "Item auction with genre item auction id: " + itemAuctionId + " has been deleted success.";
	}

	@Override
	public List<ItemAuctionDto> getAllGenreTitle(String title) {
		return itemAuctionRepository.findAllBytitle(title).stream().map(itemAucion -> extracted(itemAucion))
				.collect(Collectors.toList());
	}
}
