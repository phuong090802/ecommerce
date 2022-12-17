package com.ute.ecwebapp.dto;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAuctionDto {

	@Null
	private Integer itemAuctionId;

	@NotNull
	private String description;

	@NotNull
	private String title;

	@NotNull
	private String photo;

	@NotNull
	private byte[] photoData;

	@NotNull
	private String type;

	@NotNull
	private Double startBidAmount;

	@NotNull
	private Double autoAcceptAmount;

	@NotNull
	private Double increment;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	@NotNull
	private UserDto seller;

	@NotNull
	private GenreDto genre;

	@NotNull
	private List<BidDto> bids;

	@NotNull
	private List<BidWinnerDto> bidWinners;

	@NotNull
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
