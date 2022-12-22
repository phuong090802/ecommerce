package com.ute.ecwebapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Repository
public interface AddressRepository extends JpaRepository<AddressEntity, Integer> {
	List<AddressEntity> findAllByuser(UserEntity user);

	Optional<AddressEntity> findTopByUserOrderByUserDesc(UserEntity user);

}
