package com.ute.ecwebapp.dto;

import javax.validation.constraints.*;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerDto {
	
	@NotNull
	private BidWinnerDtoId bidWinnerId;
	
	@NotNull
	private Double shipCost;
	
	@NotNull
	private Double value;

}
