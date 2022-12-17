package com.ute.ecwebapp.dto;

import java.util.List;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {
	@Null
	private Integer genreId;

	@NotNull
	private String genreName;

	@Null
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
