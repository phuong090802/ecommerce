package com.ute.ecwebapp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "bid")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BidEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable =  false)
	private Date time;
	
	@Column(nullable =  false)
	private Double value;

	@Column(nullable =  false)
	private String status;

	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private ItemAuctionEntity itemAuction;

	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity user;

}
