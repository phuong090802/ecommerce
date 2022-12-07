package com.ute.ecwebapp.entity;

import java.util.List;

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

	@Column(length = 50, nullable = false, name = "genre_name")
	private String genreName;
	
	@OneToMany(mappedBy = "genre")
	private List<ItemAuctionEntity> itemAuctions;

	public GenreEntity(String genreName) {
		super();
		this.genreName = genreName;
	}

	public GenreEntity(Integer genreId, String genreName) {
		super();
		this.genreId = genreId;
		this.genreName = genreName;
	}
	
	
}
