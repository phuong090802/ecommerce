package com.ute.ecwebapp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerDto {

	private BidWinnerDtoId bidWinnerId;

	private Double shipCost;

	private Double value;

}
