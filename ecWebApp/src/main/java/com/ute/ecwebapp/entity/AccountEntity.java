package com.ute.ecwebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(length = 255, nullable = false)
	private String password;
	
	@OneToOne(mappedBy = "account")
	private UserEntity user;
}
