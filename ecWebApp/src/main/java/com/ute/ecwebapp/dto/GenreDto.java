package com.ute.ecwebapp.dto;

import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
	private Integer genreId;

	private String genreName;
	
	private List<ItemAuctionDto> itemAuctions;

	public GenreDto(String genreName) {
		super();
		this.genreName = genreName;
	}

	public GenreDto(Integer genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}
	
	
}
