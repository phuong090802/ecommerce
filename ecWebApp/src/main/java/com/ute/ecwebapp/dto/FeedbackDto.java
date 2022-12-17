package com.ute.ecwebapp.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

	@Null
	private Integer feadBackId;

	@Null
	private String review;

	@NotNull
	private Integer rating;

	@NotNull
	private UserDto seller;

	@NotNull
	private UserDto buyer;

	public FeedbackDto(String review, Integer rating, UserDto seller, UserDto buyer) {
		super();
		this.review = review;
		this.rating = rating;
		this.seller = seller;
		this.buyer = buyer;
	}

}
