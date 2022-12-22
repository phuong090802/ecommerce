package com.ute.ecwebapp.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.ute.ecwebapp.entity.AddressEntity;
import com.ute.ecwebapp.entity.BidEntity;
import com.ute.ecwebapp.entity.ItemAuctionEntity;
import com.ute.ecwebapp.entity.UserEntity;

@Component
public class BidWinnerUtil {
	public UserEntity getBuyer(ItemAuctionEntity itemAuction) {
		var buyerEntity = new UserEntity();
		var addressBuyer = new AddressEntity();
		for (var buyer : itemAuction.getBids()) {
			for (var address : buyer.getUser().getAddress()) {
				BeanUtils.copyProperties(buyer.getUser(), buyerEntity);
				if (address.getIsPrimary()) {
					BeanUtils.copyProperties(address, addressBuyer);
					Set<AddressEntity> setAddressEntity = new HashSet<>(Arrays.asList(addressBuyer));
					buyerEntity.setAddress(setAddressEntity);
				}
			}
		}
		return buyerEntity;
	}

	public UserEntity getSeller(ItemAuctionEntity itemAuction) {
		var sellerEntity = new UserEntity();
		var addressSeller = new AddressEntity();
		BeanUtils.copyProperties(itemAuction.getSeller(), sellerEntity);
		for (var address : itemAuction.getSeller().getAddress()) {
			if (address.getIsPrimary()) {
				BeanUtils.copyProperties(address, addressSeller);
				Set<AddressEntity> setAddressEntity = new HashSet<>(Arrays.asList(addressSeller));
				sellerEntity.setAddress(setAddressEntity);
			}
		}

		return sellerEntity;
	}

	public double getMaxPrice(List<BidEntity> listBidEntity) {
		double maxPrice = 0;
		for (var bid : listBidEntity) {
			if (bid.getValue() > maxPrice) {
				maxPrice = bid.getValue();
			}
		}
		return maxPrice;
	}
}
