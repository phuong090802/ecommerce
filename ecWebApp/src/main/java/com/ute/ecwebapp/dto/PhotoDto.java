package com.ute.ecwebapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PhotoDto {

	private Integer photoId;

	private String photoName;

	private byte[] photoData;

	private String mime;

	private ItemAuctionDto itemAuctionDto;

	public PhotoDto(String photoName, byte[] photoData, String mime, ItemAuctionDto itemAuctionDto) {
		super();
		this.photoName = photoName;
		this.photoData = photoData;
		this.mime = mime;
		this.itemAuctionDto = itemAuctionDto;
	}
	
	
}
