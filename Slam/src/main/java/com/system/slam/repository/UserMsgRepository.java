package com.system.slam.repository;

import com.system.slam.entity.UserMsg;
import com.system.slam.repository.custom.CustomUserMsgRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMsgRepository extends JpaRepository<UserMsg, Long>, CustomUserMsgRepository {
}
