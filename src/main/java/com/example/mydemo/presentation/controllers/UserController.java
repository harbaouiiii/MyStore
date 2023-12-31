package com.example.mydemo.presentation.controllers;

import com.example.mydemo.application.dtos.PasswordResetDTO;
import com.example.mydemo.application.dtos.PasswordResetRequestDTO;
import com.example.mydemo.application.dtos.UserDTO;
import com.example.mydemo.persistance.entities.Role;
import com.example.mydemo.persistance.entities.User;
import com.example.mydemo.application.exceptions.UserNotFoundException;
import com.example.mydemo.application.services.UserService;
import com.example.mydemo.utils.Roles;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping(Urls.BASE_USER_URL)
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.USER)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    @Operation(summary = Summary.GET_ALL_USERS)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping(Urls.ADMINS_URL)
    @Operation(summary = Summary.GET_ALL_ADMINS)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDTO>> getAllAdmins(){
        return ResponseEntity.ok(userService.getUsersByRole(Role.ADMIN));
    }

    @GetMapping(Urls.MODS_URL)
    @Operation(summary = Summary.GET_ALL_MODS)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<List<UserDTO>> getAllMods(){
        return ResponseEntity.ok(userService.getUsersByRole(Role.MOD));
    }

    @GetMapping(Urls.CURRENT_USER_URL)
    @Operation(summary = Summary.GET_CURRENT_USER)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal){
        return ResponseEntity.ok(userService.getUserByUsername(principal.getName()));
    }

    @GetMapping(Urls.USER_ID_PARAM)
    @Operation(summary = Summary.GET_USER_BY_ID)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDTO> getUserById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(Urls.USERNAME_URL)
    @Operation(summary = Summary.GET_USER_BY_USERNAME)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDTO> getUserByUsername(@PathVariable String username){
        try{
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping(Urls.EMAIL_URL)
    @Operation(summary = Summary.GET_USER_BY_EMAIL)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDTO> getUserByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(Urls.USER_ID_PARAM)
    @Operation(summary = Summary.UPDATE_USER)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, @PathVariable UUID id){
        try{
            userDTO.setId(id);
            return ResponseEntity.ok(userService.updateUser(userDTO, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping(Urls.DELETE_USER_URL)
    @Operation(summary = Summary.DELETE_USER_BY_USERNAME)
    @PreAuthorize(Roles.HAS_ROLE_ADMIN)
    public ResponseEntity<Map<String,Boolean>> deleteUser(@PathVariable String username){
        Map<String,Boolean> response = new HashMap<>();
        response.put("User "+userService.getUserByUsername(username)+" deleted",Boolean.TRUE);
        try{
            userService.deleteUser(username);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
