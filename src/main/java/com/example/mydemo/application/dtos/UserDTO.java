package com.example.mydemo.application.dtos;

import com.example.mydemo.persistance.entities.Role;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
}
