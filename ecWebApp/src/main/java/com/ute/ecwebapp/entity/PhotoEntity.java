package com.ute.ecwebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "photo")
public class PhotoEntity {

	@Id
	@Column(name = "photo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer photoId;

	@Column(name = "photo_name")
	private String photoName;

	@Lob
	@Column(columnDefinition = "MEDIUMBLOB", name = "photo_data")
	private byte[] photoData;

	@Column(length = 50)
	private String mime;

	@ManyToOne
	@JoinColumn(name = "item_auction_id", referencedColumnName = "item_auction_id", nullable = false)
	private ItemAuctionEntity itemAuctionEntity;

	public PhotoEntity(String photoName, byte[] photoData, String mime, ItemAuctionEntity itemAuctionEntity) {
		super();
		this.photoName = photoName;
		this.photoData = photoData;
		this.mime = mime;
		this.itemAuctionEntity = itemAuctionEntity;
	}

}
