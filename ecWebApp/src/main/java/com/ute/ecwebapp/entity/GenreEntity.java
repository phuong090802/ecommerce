package com.ute.ecwebapp.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	@Column(columnDefinition = "NVARCHAR(40)", nullable = false, name = "genre_name")
	private String genreName;
	
	@OneToMany(mappedBy = "genre")
	private Set<ItemauctionEntity> itemauctions;
}
