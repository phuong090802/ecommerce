package com.ute.ecwebapp.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Check;

import lombok.*;

@Entity
@Table(name = "item_auction")
@Check(constraints = "start_date < end_date")
@Data
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

	private String photo;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB", name = "photo_data")
	private byte[] photoData;

	@Column(length = 50)
	private String type;

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
	private UserEntity seller;

	@ManyToOne
	@JoinColumn(name = "genre_id", nullable = false)
	private GenreEntity genre;

	@OneToMany(mappedBy = "itemAuction")
	private List<BidEntity> bids;

	public ItemAuctionEntity(String description, String title, String photo, byte[] photoData, String type,
			Double startBidAmount, Double autoAcceptAmount, Double increment, Date startDate, Date endDate,
			UserEntity seller, GenreEntity genre) {
		super();
		this.description = description;
		this.title = title;
		this.photo = photo;
		this.photoData = photoData;
		this.type = type;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
		this.genre = genre;
	}

	public ItemAuctionEntity(Integer itemAuctionId, String description, String title, String photo, byte[] photoData,
			String type, Double startBidAmount, Double autoAcceptAmount, Double increment, Date startDate, Date endDate,
			UserEntity seller, GenreEntity genre) {
		super();
		this.itemAuctionId = itemAuctionId;
		this.description = description;
		this.title = title;
		this.photo = photo;
		this.photoData = photoData;
		this.type = type;
		this.startBidAmount = startBidAmount;
		this.autoAcceptAmount = autoAcceptAmount;
		this.increment = increment;
		this.startDate = startDate;
		this.endDate = endDate;
		this.seller = seller;
		this.genre = genre;
	}
	
	
}
