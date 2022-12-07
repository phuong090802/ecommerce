package com.ute.ecwebapp.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ute.ecwebapp.dto.GenreDto;
import com.ute.ecwebapp.entity.GenreEntity;
import com.ute.ecwebapp.exception.GenreNotFoundException;
import com.ute.ecwebapp.repository.GenreRepository;
import com.ute.ecwebapp.service.GenreService;

@Service
public class GenreServiceImpl implements GenreService {

	@Autowired
	private GenreRepository genreRepository;

	@Override
	public GenreDto createGenre(GenreDto genreDto) {
		GenreEntity genreEntity = new GenreEntity();
		BeanUtils.copyProperties(genreDto, genreEntity);
		genreRepository.save(genreEntity);
		return genreDto;
	}

	@Override
	public List<GenreDto> getAllGenres() {
		return genreRepository.findAll().stream().map(genre -> new GenreDto(genre.getGenreId(), genre.getGenreName()))
				.collect(Collectors.toList());
	}

	@Override
	public GenreDto getGenreById(Integer genreId) {
		GenreEntity genreEntity = genreRepository.findById(genreId)
				.orElseThrow(() -> new GenreNotFoundException(genreId));
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genreEntity, genreDto);
		return genreDto;
	}

	@Override
	public GenreDto updateGenre(GenreDto genreDto, Integer genreId) {
		GenreEntity genreEntity = genreRepository.findById(genreId)
				.orElseThrow(() -> new GenreNotFoundException(genreId));
		genreEntity.setGenreName(genreDto.getGenreName());
		genreRepository.save(genreEntity);
		return genreDto;
	}

	@Override
	public String deleteGenre(Integer genreId) {
		if (!genreRepository.existsById(genreId)) {
			throw new GenreNotFoundException(genreId);
		}
		genreRepository.deleteById(genreId);
		return "Genre with genre id: " + genreId + " has been deleted success.";
	}
	
	@Override
	public GenreDto getGenreByName(String genreName) {
		GenreEntity genreEntity = genreRepository.findBygenreName(genreName)
				.orElseThrow(() -> new GenreNotFoundException(genreName));
		GenreDto genreDto = new GenreDto();
		BeanUtils.copyProperties(genreEntity, genreDto);
		return genreDto;
	}
}
