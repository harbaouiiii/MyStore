package com.example.mydemo.services;

import com.example.mydemo.entities.Role;
import com.example.mydemo.entities.User;
import org.springframework.security.core.GrantedAuthority;

import java.security.Principal;
import java.util.Collection;
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
