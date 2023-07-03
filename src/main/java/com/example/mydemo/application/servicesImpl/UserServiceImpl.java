package com.example.mydemo.application.servicesImpl;

import com.example.mydemo.persistance.entities.Role;
import com.example.mydemo.persistance.entities.User;
import com.example.mydemo.application.exceptions.UserNotFoundException;
import com.example.mydemo.persistance.repositories.UserRepository;
import com.example.mydemo.application.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
