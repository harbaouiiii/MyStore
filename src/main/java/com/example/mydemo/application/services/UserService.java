package com.example.mydemo.application.services;

import com.example.mydemo.application.dtos.UserDTO;
import com.example.mydemo.persistance.entities.Role;
import com.example.mydemo.persistance.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<UserDTO> getAllUsers();
    UserDTO getUserById(UUID id);
    UserDTO getUserByUsername(String username);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getUsersByRole(Role role);
    UserDTO updateUser(UserDTO userDTO, UUID id);
    void deleteUser(String username);
    void updateResetPasswordToken(String token, String email);
    User getByResetPasswordToken(String token);
    void updatePassword(User user, String newPassword);

}
