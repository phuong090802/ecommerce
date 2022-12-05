package com.ute.ecwebapp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "bidwinner")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidWinnerEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "ship_cost", nullable = false, length = 12, precision = 2)
	private Double shipCost;

	@Column(nullable = false, length = 12, precision = 2)
	private Double value;

	@Id
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private ItemauctionEntity itemauction;
	
	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity user;
}
