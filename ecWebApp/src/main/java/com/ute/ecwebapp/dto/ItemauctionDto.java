package com.ute.ecwebapp.dto;

import java.sql.Date;
import java.util.List;

import org.hibernate.annotations.Check;

import lombok.*;

@Check(constraints = "start_date < end_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemAuctionDto {

	private Integer itemAuctionId;

	private String description;

	private String title;

	private String photo;

	private Double startBidAmount;

	private Double autoAcceptAmount;

	private Double increment;

	private Date startDate;

	private Date endDate;

	private UserDto user;

	private GenreDto genre;

	private List<BidDto> bids;
	
	private List<BidWinnerDto> bidWinners;
}
