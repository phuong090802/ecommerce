package com.ute.ecwebapp.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.*;

import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Null
	private Integer id;
	
	@NotNull
	private Date time;

	@NotNull
	private Double value;
	
	@NotNull
	private Integer status;
	
	@NotNull
	private ItemAuctionDto itemAuction;
	@NotNull
	private UserDto user;

}
