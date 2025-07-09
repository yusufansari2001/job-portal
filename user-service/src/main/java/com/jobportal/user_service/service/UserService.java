package com.jobportal.user_service.service;

import com.jobportal.user_service.dto.LoginRequest;
import com.jobportal.user_service.dto.UserDto;
import com.jobportal.user_service.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    String loginAndGenerateToken(LoginRequest request);
    void deleteUser(Long id);
}
