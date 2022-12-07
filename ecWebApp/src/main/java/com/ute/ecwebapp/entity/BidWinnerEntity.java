package com.ute.ecwebapp.entity;

import java.io.Serializable;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "bid_winner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private BidWinnerId bidWinnerId;
	
	@Column(nullable = false, length = 12, precision = 2)
	private Double value;
	
	@Column(name = "ship_cost", nullable = false, length = 12, precision = 2)
	private Double shipCost;

	@ManyToOne
	private ItemAuctionEntity itemAuction;

	@ManyToOne
	private UserEntity user;
}
