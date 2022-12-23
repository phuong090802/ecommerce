package com.ute.ecwebapp.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Check;

import lombok.*;

@Entity
@Table(name = "item_auction")
@Check(constraints = "start_date < end_date")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ItemAuctionEntity {
	@Id
	@Column(name = "item_auction_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer itemAuctionId;

	@Column(nullable = false, columnDefinition = "TEXT")
	private String description;

	@Column(nullable = false)
	private String title;

	@Column(name = "start_bid_amount", columnDefinition = "DECIMAL(12,2) NOT NULL DEFAULT 0")
	private Double startBidAmount;

	@Column(name = "auto_accept_amount", length = 12, precision = 2)
	private Double autoAcceptAmount;

	@Column(columnDefinition = "DECIMAL(12,2) NOT NULL DEFAULT 0")
	private Double increment;

	@Column(name = "current_price", columnDefinition = "DECIMAL(12,2) NOT NULL DEFAULT 0")
	private Double currentPrice;

	@Column(nullable = false, name = "start_date")
	private Date startDate;

	@Column(nullable = false, name = "end_date")
	private Date endDate;

	@Column(columnDefinition = "TINYINT(1) DEFAULT 0", name = "status")
	private Boolean status;

	@ManyToOne
	@JoinColumn(name = "seller_id", nullable = false)
	private UserEntity seller;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private GenreEntity genre;

	@OneToMany(mappedBy = "itemAuction", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<BidEntity> bids;

	@OneToMany(mappedBy = "itemAuctionEntity", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PhotoEntity> photos;

	public ItemAuctionEntity(String description, String title, Set<PhotoEntity> photos, Double startBidAmount,
			Double autoAcceptAmount, Double increment,Double currentPrice, Date startDate, Date endDate, UserEntity seller,
			GenreEntity genre) {
		super();
		this.description = description;
		this.title = title;
		this.photos = photos;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.currentPrice = currentPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
		this.genre = genre;
	}

	public ItemAuctionEntity(Integer itemAuctionId, String description, String title, Set<PhotoEntity> photos,
			Double startBidAmount, Double autoAcceptAmount, Double increment, Double currentPrice, Date startDate, Date endDate,
			UserEntity seller, GenreEntity genre) {
		super();
		this.itemAuctionId = itemAuctionId;
		this.description = description;
		this.title = title;
		this.photos = photos;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.currentPrice = currentPrice;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
		this.genre = genre;
	}

}
