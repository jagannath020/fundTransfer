package com.fundTransfer.fundTransfer.service;

import com.fundTransfer.fundTransfer.dto.LoginDtoRequest;
import com.fundTransfer.fundTransfer.dto.LoginDtoResponse;
import com.fundTransfer.fundTransfer.entity.DetailsTransaction;
import com.fundTransfer.fundTransfer.entity.LoginCredentials;
import com.fundTransfer.fundTransfer.pojo.LoginRequest;
import com.fundTransfer.fundTransfer.repository.DetailsTransactionRepository;
import com.fundTransfer.fundTransfer.repository.LoginRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder; // CRITICAL: Added Import
import java.util.Base64; // CRITICAL: Added Import
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    public LoginRepository loginRepository;
    @Autowired
    private DetailsTransactionRepository detailsTransactionRepository;

    // CRITICAL: Inject the PasswordEncoder for Hashing
    @Autowired
    private PasswordEncoder passwordEncoder;

    // --- Helper for CustomerName ENCODING (Base64) ---
    private String encodeUsername(String rawUsername) {
        return Base64.getEncoder().encodeToString(rawUsername.getBytes());
    }

    // --- Helper for CustomerName DECODING (Reversing Base64 for display) ---
    private String decodeUsername(String encodedUsername) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedUsername);
            return new String(decodedBytes);
        } catch (IllegalArgumentException e) {
            return "DECODING_ERROR";
        }
    }


    // --- Authentication (GET) - Corrected for Hashing and Encoding ---
    // NOTE: It is recommended to DELETE the corresponding @GetMapping("/login") from LoginController.
    @Override
    public String authenticate(LoginDtoRequest dtoRequest) {
        String encodedUsername = encodeUsername(dtoRequest.getCustomerName());
        LoginCredentials loginCredentials = loginRepository.findByCustomerName(encodedUsername);

        if (loginCredentials != null &&
                passwordEncoder.matches(dtoRequest.getPassword(), loginCredentials.getPassword())) {
            return "Login successful!";
        } else {
            return "Invalid credentials.";
        }
    }

    @Override
    public LoginDtoResponse authenticateUsingPost(LoginDtoRequest dtoRequest) {

        // 1. Find the user by Base64 encoded username
        String encodedUsername = encodeUsername(dtoRequest.getCustomerName());
        LoginCredentials credentials = loginRepository.findByCustomerName(encodedUsername);

        LoginDtoResponse response = new LoginDtoResponse();

        if (credentials != null) {
            // 2. Use the injected PasswordEncoder to safely match the raw password against the stored hash
            if (passwordEncoder.matches(dtoRequest.getPassword(), credentials.getPassword())) {
                response.setStatus("SUCCESS");
                response.setMessage("Login successful");
                response.setCustomerId(credentials.getCustomerId());
            } else {
                response.setStatus("FAILURE");
                response.setMessage("Invalid username or password (Password mismatch)");
            }
        } else {
            response.setStatus("FAILURE");
            response.setMessage("Invalid username or password (User not found)");
        }

        return response;
    }

    @Override
    @Transactional
    public LoginCredentials createNewUser(LoginRequest request) {

        DetailsTransaction transaction = new DetailsTransaction();
        DetailsTransaction savedTransaction = detailsTransactionRepository.save(transaction);
        LoginCredentials newUserLogin = new LoginCredentials();

        // 1. Base64 Encode the Customer Name
        String encodedName = encodeUsername(request.getCustomerName());
        newUserLogin.setCustomerName(encodedName);

        // 2. HASH the Password
        String hashedPassword = passwordEncoder.encode(request.getPassword());
        newUserLogin.setPassword(hashedPassword); // <<< CRITICAL: HASH THE PASSWORD

        newUserLogin.setCreationDate(new Date(System.currentTimeMillis()));
        newUserLogin.setTransactionId(savedTransaction.getTransactionId());
        return loginRepository.save(newUserLogin);
    }

    // --- NEW METHOD: Retrieve all users and decode usernames (for the secured endpoint) ---
    @Override
    public List<LoginCredentials> getAllUsers() {
        List<LoginCredentials> allUsers = loginRepository.findAll();

        return allUsers.stream()
                .map(user -> {
                    // Clone the entity and decode username for display, while omitting the password hash
                    LoginCredentials decodedUser = new LoginCredentials();
                    decodedUser.setCustomerId(user.getCustomerId());
                    decodedUser.setTransactionId(user.getTransactionId());
                    decodedUser.setCreationDate(user.getCreationDate());
                    decodedUser.setCustomerName(decodeUsername(user.getCustomerName()));
                    // Password hash is intentionally NOT set here (will be null in the JSON response)
                    return decodedUser;
                })
                .collect(Collectors.toList());
    }
}