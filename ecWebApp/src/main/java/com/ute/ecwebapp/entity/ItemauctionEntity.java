package com.ute.ecwebapp.entity;

import java.sql.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Check;

import lombok.*;

@Entity
@Table(name = "item_auction")
@Check(constraints = "start_date < end_date")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemauctionEntity {
	@Id
	@Column(name = "item_auction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemAuctionId;

	@Column(nullable = false)
	private String description;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
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
	@JoinColumn(name = "genre_id", nullable = false)
	private GenreEntity genre;

	@OneToMany(mappedBy = "itemAuction")
	private Set<BidEntity> bids;
}
