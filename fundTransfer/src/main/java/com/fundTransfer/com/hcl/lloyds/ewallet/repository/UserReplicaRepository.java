package com.fundTransfer.com.hcl.lloyds.ewallet.repository;

import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserEntity;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserReplica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserReplicaRepository extends JpaRepository<UserReplica, Integer> {
    Optional<UserEntity> findByEmail(String email);

    Optional<UserEntity> findByPhone(String phone);

    void deleteByDeptNoAndEmailAndPhone(Integer deptNo, String email, String phone);
    @Modifying
    @Query("DELETE FROM UserReplica u WHERE u.email = :email AND u.phone = :phone")
    void deleteByEmailAndPhone(String email, String phone);
}
