package com.fundTransfer.com.hcl.lloyds.ewallet.repository;




import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByPhone(String phone);
    Optional<UserEntity> findByName(String name);
}