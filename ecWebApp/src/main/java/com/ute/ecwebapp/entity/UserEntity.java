package com.ute.ecwebapp.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(name = "user_name", columnDefinition = "NVARCHAR(40)", nullable = false)
	private String userName;

	@Column(length = 10)
	private String phone;

	@Column(length = 255)
	private String email;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "account_id", referencedColumnName = "account_id")
	private AccountEntity account;

	@OneToMany(mappedBy = "user")
	private Set<AddressEntity> address;

	@OneToMany(mappedBy = "user")
	private Set<ItemauctionEntity> itemauctions;

	@OneToMany(mappedBy = "user")
	private Set<FeedbackEntity> feedbacks;

	@OneToMany(mappedBy = "user")
	private Set<FeedbackEntity> _feedbacks;
	
	@OneToMany(mappedBy = "user")
	private Set<BidEntity> bids;
}
