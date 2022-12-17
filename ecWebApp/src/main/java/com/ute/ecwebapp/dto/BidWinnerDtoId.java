package com.ute.ecwebapp.dto;

import java.io.Serializable;

import javax.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerDtoId implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private ItemAuctionDto itemAuction;
	
	@NotNull
	private UserDto buyer;

}
