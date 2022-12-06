package com.ute.ecwebapp.dto;

import org.hibernate.annotations.Check;

import lombok.*;

@Check(constraints = "rating BETWEEN 1 AND 5")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDto {

	private Integer feadBackId;

	private String review;

	private Integer rating;

	private UserDto user;

	private UserDto _user;

}
