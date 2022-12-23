package com.ute.ecwebapp.entity;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BidWinnerEntityId implements Serializable {

	private static final long serialVersionUID = 1L;

	@OneToOne
	@JoinColumn(name = "item_auction_id", nullable = false)
	private ItemAuctionEntity itemAuction;

	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity buyer;

}
