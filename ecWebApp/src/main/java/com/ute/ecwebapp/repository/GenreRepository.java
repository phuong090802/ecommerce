package com.ute.ecwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.GenreEntity;

@Repository
public interface GenreRepository extends JpaRepository<GenreEntity, Integer> {
	Optional<GenreEntity> findBygenreName(String genreName);

	List<GenreEntity> findBygenreNameContaining(String genreName);
}
