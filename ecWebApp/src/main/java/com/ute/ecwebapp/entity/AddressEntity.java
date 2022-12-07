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

	@Column(nullable = false, name = "full_address")
	private String fullAddress;

	@Column(nullable = false)
	private String state;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private UserEntity user;

	public AddressEntity(String fullAddress, String state, UserEntity user) {
		super();
		this.fullAddress = fullAddress;
		this.state = state;
		this.user = user;
	}

}
