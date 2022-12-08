package com.ute.ecwebapp.dto;

import java.util.List;

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

	private List<AddressDto> address;

	private List<ItemAuctionDto> itemAuctions;

	private List<FeedbackDto> feedbackSellers;

	private List<FeedbackDto> feedbackBuyers;

	private List<BidDto> bids;

	private List<BidWinnerDto> bidWinners;

	public UserDto(String userName, String phone, String email) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.email = email;
	}

	public UserDto(Integer userId, String userName, String phone, String email) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
	}

	public UserDto(String userName, String phone, String email, AccountDto account, List<AddressDto> address) {
		super();
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.account = account;
		this.address = address;
	}

	public UserDto(Integer userId, String userName, String phone, String email, List<AddressDto> address) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

}
