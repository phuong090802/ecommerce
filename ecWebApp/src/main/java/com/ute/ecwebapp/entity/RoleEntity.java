package com.ute.ecwebapp.entity;

import java.util.Set;

import javax.persistence.*;

import com.ute.ecwebapp.config.RoleName;

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
	@Enumerated(EnumType.STRING)
	private RoleName roleName;

	@OneToMany(mappedBy = "role")
	private Set<AccountEntity> accounts;

}
