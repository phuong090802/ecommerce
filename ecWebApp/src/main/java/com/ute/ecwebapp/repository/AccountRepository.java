package com.ute.ecwebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.AccountEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity, Integer> {
	boolean existsByaccountName(String accountName);

	AccountEntity findByuser(UserEntity user);

	Optional<AccountEntity> findByaccountName(String accountName);
}
