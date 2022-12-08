package com.ute.ecwebapp.dto;

import java.sql.Date;
import java.util.List;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAuctionDto {

	private Integer itemAuctionId;

	private String description;

	private String title;

	private String photo;

	private byte[] photoData;

	private String type;

	private Double startBidAmount;

	private Double autoAcceptAmount;

	private Double increment;

	private Date startDate;

	private Date endDate;

	private UserDto seller;

	private GenreDto genre;

	private List<BidDto> bids;

	private List<BidWinnerDto> bidWinners;

	public ItemAuctionDto(String description, String title, String photo, byte[] photoData, String type,
			Double startBidAmount, Double autoAcceptAmount, Double increment, Date startDate, Date endDate,
			UserDto seller) {
		super();
		this.description = description;
		this.title = title;
		this.photo = photo;
		this.photoData = photoData;
		this.type = type;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
	}

	public ItemAuctionDto(Integer itemAuctionId, String description, String title, String type, Double startBidAmount,
			Double autoAcceptAmount, Double increment, Date startDate, Date endDate, UserDto seller, GenreDto genre) {
		super();
		this.itemAuctionId = itemAuctionId;
		this.description = description;
		this.title = title;
		this.type = type;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
		this.genre = genre;
	}

}
