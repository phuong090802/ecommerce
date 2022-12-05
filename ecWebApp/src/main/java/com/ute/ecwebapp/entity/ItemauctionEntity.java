package com.ute.ecwebapp.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Check;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "itemauction")
@Check(constraints = "start_date < end_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemauctionEntity {
	@Id
	@Column(name = "item_auction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemAuctionId;

	@Column(columnDefinition = "NVARCHAR(255)", nullable = false)
	private String description;

	@Column(columnDefinition = "NVARCHAR(255)", nullable = false)
	private String title;

	@Column(length = 255, nullable = false)
	private String photo;

	@Column(name = "start_bid_amount", columnDefinition = "DECIMAL(12,2) NOT NULL DEFAULT 0")
	private Double startBidAmount;

	@Column(name = "auto_accept_amount", length = 12, precision = 2)
	private Double autoAcceptAmount;

	@Column(columnDefinition = "DECIMAL(12,2) NOT NULL DEFAULT 0")
	private Double increment;

	@Column(nullable = false, name = "start_date")
	private Date startDate;

	@Column(nullable = false, name = "end_date")
	private Date endDate;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private UserEntity user;

	@ManyToOne
	@JoinColumn(name = "gener_id", nullable = false)
	private GenreEntity genre;

	@OneToMany(mappedBy = "itemauction")
	private Set<BidWinnerEntity> bidWinners;

	@OneToMany(mappedBy = "itemauction")
	private Set<BidEntity> bids;
}
