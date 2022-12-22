package com.ute.ecwebapp.entity;

import java.util.Date;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "account")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountEntity {

	@Id
	@Column(name = "account_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer accountId;

	@Column(name = "user_name", length = 50, nullable = false, unique = true)
	private String userName;

	@Column(nullable = false)
	private String password;

	private String otp;

	private Date expire;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id", nullable = false)
	private RoleEntity role;

	public AccountEntity(String userName, String password, RoleEntity role) {
		super();
		this.userName = userName;
		this.password = password;
		this.role = role;
	}

	public AccountEntity(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

}
