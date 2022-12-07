package com.ute.ecwebapp.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDto implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private LocalDateTime time;

	private Double value;

	private String status;

	private ItemAuctionDto itemAuction;

	private UserDto user;

}
