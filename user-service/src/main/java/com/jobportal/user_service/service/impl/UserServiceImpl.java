package com.jobportal.user_service.service.impl;

import com.jobportal.user_service.dto.LoginRequest;
import com.jobportal.user_service.dto.UserDto;
import com.jobportal.user_service.entity.User;
import com.jobportal.user_service.exception.ResourceNotFoundException;
import com.jobportal.user_service.repository.UserRepository;
import com.jobportal.user_service.service.UserService;
import com.jobportal.user_service.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public UserDto createUser(UserDto userDto) {
        User user = mapToEntity(userDto);

        // Hash password before saving
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        User savedUser = userRepository.save(user);
        return mapToDto(savedUser);
    }

    @Override
    public String loginAndGenerateToken(LoginRequest request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));

        // Check if password matches
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid email or password");
        }

        // Generate JWT token using email
        return jwtUtil.generateToken(user.getEmail());
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResourceNotFoundException("No users found in the database.");
        }
        return users.stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return mapToDto(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new ResourceNotFoundException("Cannot delete. User with id " + id + " does not exist.");
        }
        userRepository.deleteById(id);
    }

    private UserDto mapToDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                //.password(user.getPassword())  // only needed internally; exclude in response if needed
                .build();
    }

    private User mapToEntity(UserDto dto) {
        return User.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .role(dto.getRole())
                .password(dto.getPassword())
                .build();
    }
}
