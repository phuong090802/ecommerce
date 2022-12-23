package com.ute.ecwebapp.dto;

import java.util.Date;
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
	private List<PhotoDto> photos;

	@NotNull
	private Double startBidAmount;

	@NotNull
	private Double autoAcceptAmount;

	@NotNull
	private Double increment;

	@NotNull
	private Double currentPrice;

	@NotNull
	private Date startDate;

	@NotNull
	private Date endDate;

	@NotNull
	private Boolean status;

	@NotNull
	private UserDto seller;

	@NotNull
	private GenreDto genre;

	@NotNull
	private List<BidDto> bids;

	@NotNull
	private List<BidWinnerDto> bidWinners;

	@NotNull
	public ItemAuctionDto(String description, String title, List<PhotoDto> photos, Double startBidAmount,
			Double autoAcceptAmount, Double increment, Double currentPrice, Date startDate, Date endDate,
			Boolean status, UserDto seller) {
		super();
		this.description = description;
		this.title = title;
		this.photos = photos;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.currentPrice = currentPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.seller = seller;
	}

	public ItemAuctionDto(Integer itemAuctionId, String description, String title, List<PhotoDto> photos,
			Double startBidAmount, Double autoAcceptAmount, Double increment, Double currentPrice, Date startDate,
			Date endDate, Boolean status, UserDto seller, GenreDto genre) {
		super();
		this.itemAuctionId = itemAuctionId;
		this.description = description;
		this.title = title;
		this.photos = photos;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.currentPrice = currentPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.seller = seller;
		this.genre = genre;
	}

}
