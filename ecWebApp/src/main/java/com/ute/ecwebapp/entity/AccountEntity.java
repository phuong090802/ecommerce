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

	@Column(name = "account_name", length = 50, nullable = false, unique = true)
	private String accountName;

	@Column(nullable = false)
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
	private UserEntity user;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private RoleEntity role;

	public AccountEntity(String accountName, String password, RoleEntity role) {
		super();
		this.accountName = accountName;
		this.password = password;
		this.role = role;
	}

	public AccountEntity(String accountName, String password) {
		super();
		this.accountName = accountName;
		this.password = password;
	}

}
