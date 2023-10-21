package com.device.allocation.repository;

import com.device.allocation.entity.DeviceUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceUserRepository extends JpaRepository<DeviceUser,Integer> {
}
