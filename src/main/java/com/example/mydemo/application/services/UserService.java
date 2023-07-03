package com.example.mydemo.application.services;

import com.example.mydemo.persistance.entities.Role;
import com.example.mydemo.persistance.entities.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    List<User> getAllUsers();
    User getUserById(UUID id);
    User getUserByUsername(String username);
    User getUserByEmail(String email);
    List<User> getUsersByRole(Role role);
    User updateUser(User user, UUID id);
    void deleteUser(String username);

}
