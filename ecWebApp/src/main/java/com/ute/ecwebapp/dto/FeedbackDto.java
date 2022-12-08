package com.ute.ecwebapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {
	private Integer feadBackId;

	private String review;

	private Integer rating;

	private UserDto seller;

	private UserDto buyer;

	public FeedbackDto(String review, Integer rating, UserDto seller, UserDto buyer) {
		super();
		this.review = review;
		this.rating = rating;
		this.seller = seller;
		this.buyer = buyer;
	}
	
	
}
