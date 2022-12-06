package com.ute.ecwebapp.entity;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "user_name", length = 40, nullable = false, unique = true)
	private String userName;

	@Column(nullable = false)
	private String password;
	
	@OneToOne(mappedBy = "account")
	private UserEntity user;

	public AccountEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	
}
