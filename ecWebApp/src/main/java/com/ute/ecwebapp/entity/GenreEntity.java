package com.ute.ecwebapp.entity;

import java.util.Set;

import javax.persistence.*;


import lombok.*;


@Entity
@Table(name = "genre")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GenreEntity {
	@Id
	@Column(name = "genre_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer genreId;

	@Column(length = 40, nullable = false, name = "genre_name")
	private String genreName;
	
	@OneToMany(mappedBy = "genre")
	private Set<ItemauctionEntity> itemAuctions;
}
