package com.ute.ecwebapp.entity;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "bid_winner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerEntity {

	@EmbeddedId
	private BidWinnerEntityId bidWinnerId;

	@Column(nullable = false, length = 12, precision = 2)
	private Double value;

	@Column(name = "ship_cost", nullable = false, length = 12, precision = 2)
	private Double shipCost;
}
