package com.ute.ecwebapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.BidEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;

@Repository
public interface BidRepository extends JpaRepository<BidEntity, Integer> {
	Optional<BidEntity> findTopByItemAuctionOrderByItemAuctionDesc(ItemAuctionEntity itemAuction);
}
