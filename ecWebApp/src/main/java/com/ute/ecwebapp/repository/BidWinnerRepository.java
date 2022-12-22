package com.ute.ecwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.BidWinnerEntity;
import com.ute.ecwebapp.entity.BidWinnerEntityId;

@Repository
public interface BidWinnerRepository extends JpaRepository<BidWinnerEntity, BidWinnerEntityId> {

}
