package com.ute.ecwebapp.dto;

import java.io.Serializable;


import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private BidWinnerId bidWinnerId;

	private Double shipCost;

	private Double value;
	
	private ItemAuctionDto itemAuction;

	private UserDto user;
}
