package com.ute.ecwebapp.entity;

import java.util.Set;

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

	@Column(name = "user_name", length = 40, nullable = false)
	private String userName;

	@Column(length = 10)
	private String phone;

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
