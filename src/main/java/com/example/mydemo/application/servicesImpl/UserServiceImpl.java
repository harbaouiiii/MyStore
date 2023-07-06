package com.example.mydemo.application.servicesImpl;

import com.example.mydemo.application.dtos.UserDTO;
import com.example.mydemo.application.mappers.UserMapper;
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
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getAllUsers() {
        return userMapper.toUserDTO(userRepository.findAll());
    }

    @Override
    public UserDTO getUserById(UUID id) {
        return userMapper.toUserDTO(userRepository.findById(id).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        return userMapper.toUserDTO(userRepository.findByUserName(username).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userMapper.toUserDTO(userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new));
    }

    @Override
    public List<UserDTO> getUsersByRole(Role role) {
        return userMapper.toUserDTO(userRepository.findUsersByRole(role));
    }


    @Override
    public UserDTO updateUser(UserDTO userDTO, UUID id) {
        User userToUpdate = userRepository.findById(id).orElseThrow(UserNotFoundException::new);
        userToUpdate.setId(id);
        return userMapper.toUserDTO(userRepository.save(userMapper.toUser(userDTO)));
    }

    @Override
    public void deleteUser(String username) {
        User user = userRepository.findByUserName(username).orElseThrow(UserNotFoundException::new);
        userRepository.delete(user);
    }
}
