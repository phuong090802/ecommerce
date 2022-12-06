package com.ute.ecwebapp.entity;

import javax.persistence.*;


import lombok.*;

@Entity
@Table(name = "address")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressEntity {
	@Id
	@Column(name = "address_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;
	
	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private String state;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;
}
