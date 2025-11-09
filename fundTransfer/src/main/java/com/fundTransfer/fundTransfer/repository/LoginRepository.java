package com.fundTransfer.fundTransfer.repository;
import com.fundTransfer.fundTransfer.entity.LoginCredentials;
import org.springframework.data.jpa.repository.JpaRepository;


public interface LoginRepository extends JpaRepository<LoginCredentials, Integer> {

    LoginCredentials findByPassword(String password);
    LoginCredentials findByCustomerName(String customerName);

    LoginCredentials findByCustomerNameAndPassword(String customerName, String password);

}
