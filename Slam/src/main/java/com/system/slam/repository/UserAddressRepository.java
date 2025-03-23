package com.system.slam.repository;

import com.system.slam.entity.UserAddress;
import com.system.slam.repository.custom.CustomUserAddressRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long>, CustomUserAddressRepository {
}
