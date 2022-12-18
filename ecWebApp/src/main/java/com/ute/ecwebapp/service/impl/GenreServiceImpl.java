package com.ute.ecwebapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.entity.GenreEntity;
import com.ute.ecwebapp.exception.BadRequestException;
import com.ute.ecwebapp.repository.GenreRepository;
import com.ute.ecwebapp.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public void createGenre(GenreDto genreDto) {
		var genreEntity = new GenreEntity();
		BeanUtils.copyProperties(genreDto, genreEntity);
		genreRepository.save(genreEntity);
	}

	@Override
	public List<GenreDto> getAllGenres() {
		return genreRepository.findAll().stream().map(genre -> new GenreDto(genre.getGenreId(), genre.getGenreName()))
				.collect(Collectors.toList());
	}

	@Override
	public GenreDto getGenreById(Integer genreId) {
		var genreEntity = genreRepository.findById(genreId).orElseThrow(
				() -> new BadRequestException("Could not found the genre with genre id: " + genreId + "."));
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genreEntity, genreDto);
		return genreDto;
	}

	@Override
	public void updateGenre(GenreDto genreDto, Integer genreId) {
		var genreEntity = genreRepository.findById(genreId).orElseThrow(
				() -> new BadRequestException("Could not found the genre with genre id: " + genreId + "."));
		genreEntity.setGenreName(genreDto.getGenreName());
		genreRepository.save(genreEntity);
	}

	@Override
	public void deleteGenre(Integer genreId) {
		if (!genreRepository.existsById(genreId)) {
			throw new BadRequestException("Could not found the genre with genre id: " + genreId + ".");
		}
		genreRepository.deleteById(genreId);
	}

	@Override
	public GenreDto getGenreByName(String genreName) {
		var genreEntity = genreRepository.findBygenreName(genreName).orElseThrow(
				() -> new BadRequestException("Could not found the genre with genre name: " + genreName + "."));
		var genreDto = new GenreDto();
		BeanUtils.copyProperties(genreEntity, genreDto);
		return genreDto;
	}

	@Override
	public List<GenreDto> getLikeGenreByName(String genreName) {
		return genreRepository.findBygenreNameContaining(genreName).stream()
				.map(genre -> new GenreDto(genre.getGenreId(), genre.getGenreName())).collect(Collectors.toList());
	}
}
