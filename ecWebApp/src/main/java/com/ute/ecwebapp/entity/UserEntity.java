package com.ute.ecwebapp.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {

	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;

	@Column(name = "full_name", length = 50, nullable = false)
	private String fullName;

	@Column(length = 11, nullable = false, unique = true)
	private String phone;

	@Column(nullable = false, unique = true)
	private String email;

	@OneToOne(mappedBy = "user")
	private AccountEntity account;

	@OneToMany(mappedBy = "user")
	private List<AddressEntity> address;

	@OneToMany(mappedBy = "seller")
	private List<ItemAuctionEntity> itemAuctions;

	@OneToMany(mappedBy = "seller")
	private List<FeedbackEntity> feedbackSellers;

	@OneToMany(mappedBy = "buyer")
	private List<FeedbackEntity> feedbackBuyers;

	@OneToMany(mappedBy = "user")
	private List<BidEntity> bids;

	public UserEntity(String fullName, String phone, String email) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
	}

	public UserEntity(Integer userId, String fullName, String phone, String email) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
	}

	public UserEntity(String fullName, String phone, String email, AccountEntity account, List<AddressEntity> address) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.account = account;
		this.address = address;
	}

	public UserEntity(Integer userId, String fullName, String phone, String email, List<AddressEntity> address) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

}
