package com.ute.ecwebapp.entity;

import java.util.Set;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "user")
@Setter
@Getter
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

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<AddressEntity> address;

	@OneToMany(mappedBy = "seller")
	private Set<ItemAuctionEntity> itemAuctions;

	@OneToMany(mappedBy = "seller")
	private Set<FeedbackEntity> feedbackSellers;
	
	
	@OneToMany(mappedBy = "buyer")
	private Set<FeedbackEntity> feedbackBuyers;

	
	@OneToMany(mappedBy = "user")
	private Set<BidEntity> bids;

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

	public UserEntity(String fullName, String phone, String email, AccountEntity account, Set<AddressEntity> address) {
		super();
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.account = account;
		this.address = address;
	}

	public UserEntity(Integer userId, String fullName, String phone, String email, Set<AddressEntity> address) {
		super();
		this.userId = userId;
		this.fullName = fullName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}

}
