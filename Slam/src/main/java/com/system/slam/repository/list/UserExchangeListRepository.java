package com.system.slam.repository.list;

import com.system.slam.entity.list.UserExchangeList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserExchangeListRepository extends JpaRepository<UserExchangeList, Long> {
}
