package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.UserDTO;
import com.example.mydemo.persistance.entities.User;
import com.example.mydemo.persistance.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapperImpl implements UserMapper {
    private final UserRepository userRepository;
    @Override
    public UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole()
        );
    }

    @Override
    public User toUser(UserDTO userDTO) {
        return new User(
                userDTO.getId(),
                userDTO.getUserName(),
                userDTO.getFirstName(),
                userDTO.getLastName(),
                userDTO.getEmail(),
                userRepository.findByUserName(userDTO.getUserName()).orElseThrow().getPassword(),
                userDTO.getRole()
        );
    }

    @Override
    public List<UserDTO> toUserDTO(List<User> users) {
        List<UserDTO> userDTOs = new ArrayList<>();
        for (User user : users) {
            userDTOs.add(toUserDTO(user));
        }
        return userDTOs;
    }

    @Override
    public List<User> toUser(List<UserDTO> userDTOs) {
        List<User> users = new ArrayList<>();
        for (UserDTO userDTO : userDTOs) {
            users.add(toUser(userDTO));
        }
        return users;
    }
}
