package com.fundTransfer.com.hcl.lloyds.ewallet.service;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserEntity;
import com.fundTransfer.com.hcl.lloyds.ewallet.exception.CustomException;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.UserReplicaRepository;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserReplicaRepository userReplicaRepository;

    public UserResponseDto createUser(UserRequestDto dto) {

        log.info("Attempting to create user with email: {}, phone: {}", dto.getEmail(), dto.getPhone());

        validateUniqueField("email", dto.getEmail());
        validateUniqueField("phone", dto.getPhone());
        // DO NOT validate name (your DB allows duplicates)

        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());
        user.setDeptNo(dto.getDeptNo());   // ✅ MUST BE ADDED

        UserEntity savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getUserId());

        return new UserResponseDto(
                savedUser.getUserId(),
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhone()
        );
    }

    // Unique email + phone checker
    private void validateUniqueField(String field, String value) {

        log.debug("Validating unique {}: {}", field, value);

        boolean exists = switch (field) {
            case "email" -> userRepository.findByEmail(value).isPresent();
            case "phone" -> userRepository.findByPhone(value).isPresent();
            default      -> false;
        };

        if (exists) {
            log.error("Duplicate {} found: {}", field, value);
            throw new CustomException(
                    field.substring(0, 1).toUpperCase() + field.substring(1) + " already exists."
            );
        }

        log.debug("{} is unique: {}", field, value);
    }

    public UserResponseDto getUserById(Integer userId) {
        return userRepository.findById(userId)
                .map(user -> new UserResponseDto(
                        user.getUserId(), user.getName(), user.getEmail(), user.getPhone()
                ))
                .orElseThrow(() -> new CustomException("User not found with ID: " + userId));
    }

    public List<UserResponseDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(user -> new UserResponseDto(
                        user.getUserId(), user.getName(), user.getEmail(), user.getPhone()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteUser(Integer deptNo, String email, String phone) {
        userRepository.deleteByDeptNoAndEmailAndPhone(deptNo, email, phone);
        log.info("User deleted successfully with deptNo: {}", deptNo);
    }
    @Transactional
    public void deleteUsers(String email, String phone) {
        userRepository.deleteByEmailAndPhone(email, phone);
        log.info("User deleted successfully with email: {}", email);
    }
    @Transactional
    public void deleteUsersReplica(String email, String phone) {
        userReplicaRepository.deleteByEmailAndPhone(email, phone);
        log.info("User deleted successfully with email: {}", email);
    }

}