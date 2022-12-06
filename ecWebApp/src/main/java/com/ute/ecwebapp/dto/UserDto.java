package com.ute.ecwebapp.dto;

import java.util.Set;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	private Integer userId;

	private String userName;

	private String phone;

	private String email;

	private AccountDto account;

	private Set<AddressDto> address;

	private Set<ItemauctionDto> itemauctions;

	private Set<FeedbackDto> feedbacks;

	private Set<FeedbackDto> _feedbacks;
	
	private Set<BidDto> bids;
}
