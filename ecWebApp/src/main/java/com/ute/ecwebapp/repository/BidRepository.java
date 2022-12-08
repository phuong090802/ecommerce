package com.ute.ecwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.BidEntity;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Integer> {

}
