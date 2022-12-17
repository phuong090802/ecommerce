package com.ute.ecwebapp.dto;

import java.util.List;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

	@Null
	private Integer userId;

	@NotNull
	private String userName;

	@Null
	private String phone;

	@NotNull
	@Email
	private String email;

	@NotNull
	private AccountDto account;

	@NotNull
	@NotEmpty
	private List<AddressDto> address;

	@Null
	private List<ItemAuctionDto> itemAuctions;

	@Null
	private List<FeedbackDto> feedbackSellers;

	@Null
	private List<FeedbackDto> feedbackBuyers;

	@Null
	private List<BidDto> bids;

	@Null
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
