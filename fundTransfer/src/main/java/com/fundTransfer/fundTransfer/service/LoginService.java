package com.fundTransfer.fundTransfer.service;


import com.fundTransfer.fundTransfer.dto.LoginDtoRequest;
import com.fundTransfer.fundTransfer.dto.LoginDtoResponse;
import com.fundTransfer.fundTransfer.entity.LoginCredentials;
import com.fundTransfer.fundTransfer.pojo.LoginRequest;

import java.util.List;

public interface LoginService {

    public String authenticate(LoginDtoRequest dtoRequest);
    public LoginDtoResponse authenticateUsingPost(LoginDtoRequest dtoRequest);
    LoginCredentials createNewUser(LoginRequest request);
    List<LoginCredentials> getAllUsers();


}
