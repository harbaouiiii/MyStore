package com.example.mydemo.application.mappers;

import com.example.mydemo.application.dtos.UserDTO;
import com.example.mydemo.persistance.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        uses = {}
)
public interface UserMapper {
    UserDTO toUserDTO(User user);
    User toUser(UserDTO userDTO);
    List<UserDTO> toUserDTO(List<User> users);
    List<User> toUser(List<UserDTO> userDTOs);
}
