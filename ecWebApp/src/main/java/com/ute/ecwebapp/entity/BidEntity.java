package com.ute.ecwebapp.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private LocalDateTime time;

	private Double value;

	private String status;

	@Id
	@ManyToOne
	@JoinColumn(name = "item_id", nullable = false)
	private ItemauctionEntity itemauction;

	@Id
	@ManyToOne
	@JoinColumn(name = "buy_id", nullable = false)
	private UserEntity user;

}
