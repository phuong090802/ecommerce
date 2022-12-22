package com.ute.ecwebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ute.ecwebapp.entity.ItemAuctionEntity;

@Repository
public interface ItemAuctionRepository extends JpaRepository<ItemAuctionEntity, Integer> {

	List<ItemAuctionEntity> findAllBytitle(String title);
}
