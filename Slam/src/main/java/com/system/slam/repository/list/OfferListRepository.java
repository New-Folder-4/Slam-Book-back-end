package com.system.slam.repository.list;

import com.system.slam.entity.list.OfferList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfferListRepository extends JpaRepository<OfferList, Long> {
}
