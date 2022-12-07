package com.ute.ecwebapp.entity;

import java.util.List;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name = "role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleEntity {
	@Id
	@Column(name = "role_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer roleId;

	@Column(name = "role_name", nullable = false, length = 50)
	private String roleName;

	@OneToMany(mappedBy = "role")
	private List<AccountEntity> accounts;

}
