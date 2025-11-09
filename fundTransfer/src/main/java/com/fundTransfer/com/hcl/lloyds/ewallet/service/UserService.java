package com.fundTransfer.com.hcl.lloyds.ewallet.service;


import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserRequestDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.dto.UserResponseDto;
import com.fundTransfer.com.hcl.lloyds.ewallet.entity.UserEntity;
import com.fundTransfer.com.hcl.lloyds.ewallet.exception.CustomException;
import com.fundTransfer.com.hcl.lloyds.ewallet.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponseDto createUser(UserRequestDto dto) {

        log.info("Attempting to create user with email: {}, phone: {}", dto.getEmail(), dto.getPhone());

        validateUniqueField("email", dto.getEmail());
        validateUniqueField("phone", dto.getPhone());
        validateUniqueField("name", dto.getName());

        UserEntity user = new UserEntity();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPhone(dto.getPhone());

        UserEntity savedUser = userRepository.save(user);

        log.info("User created successfully with ID: {}", savedUser.getUserId());

        return new UserResponseDto(
                savedUser.getUserId(), savedUser.getName(), savedUser.getEmail(), savedUser.getPhone()
        );
    }

    // Reusable helper for unique field checks
    private void validateUniqueField(String field, String value) {

        log.debug("Validating unique {}: {}", field, value);

        boolean exists = switch (field) {
            case "email" -> userRepository.findByEmail(value).isPresent();
            case "phone" -> userRepository.findByPhone(value).isPresent();
            case "name"  -> userRepository.findByName(value).isPresent();
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
}