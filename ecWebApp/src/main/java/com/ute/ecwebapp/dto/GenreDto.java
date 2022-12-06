package com.ute.ecwebapp.dto;

import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreDto {

	private Integer genreId;

	private String genreName;
	
	private Set<ItemauctionDto> itemAuctions;
}
