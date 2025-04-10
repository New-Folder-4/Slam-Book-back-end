package com.system.slam.repository.list;

import com.system.slam.entity.list.ExchangeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeListRepository extends JpaRepository<ExchangeList, Long> {
}
