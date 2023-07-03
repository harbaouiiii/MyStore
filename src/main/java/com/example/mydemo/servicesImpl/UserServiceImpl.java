package com.example.mydemo.servicesImpl;

import com.example.mydemo.entities.Role;
import com.example.mydemo.entities.User;
import com.example.mydemo.exceptions.UserNotFoundException;
import com.example.mydemo.repositories.UserRepository;
import com.example.mydemo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUserName(username).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public List<User> getUsersByRole(Role role) {
        return userRepository.findUsersByRole(role);
    }


    @Override
    public User updateUser(User user, UUID id) {
        User userToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userToUpdate.setId(id);
        return userRepository.save(userToUpdate);
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
