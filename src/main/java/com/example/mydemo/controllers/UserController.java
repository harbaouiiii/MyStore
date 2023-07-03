package com.example.mydemo.controllers;

import com.example.mydemo.entities.Role;
import com.example.mydemo.entities.User;
import com.example.mydemo.exceptions.UserNotFoundException;
import com.example.mydemo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "Get all users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping("/admins")
    @Operation(summary = "Get all admins")
    public ResponseEntity<List<User>> getAllAdmins(){
        return ResponseEntity.ok(userService.getUsersByRole(Role.ADMIN));
    }

    @GetMapping("/mods")
    @Operation(summary = "Get all mods")
    public ResponseEntity<List<User>> getAllMods(){
        return ResponseEntity.ok(userService.getUsersByRole(Role.MOD));
    }

    @GetMapping("/current")
    @Operation(summary = "Get current user")
    public ResponseEntity<User> getCurrentUser(Principal principal){
        return ResponseEntity.ok(userService.getUserByUsername(principal.getName()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserById(@PathVariable UUID id){
        try{
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/username/{username}")
    @Operation(summary = "Get user by username")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username){
        try{
            return ResponseEntity.ok(userService.getUserByUsername(username));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/email/{email}")
    @Operation(summary = "Get user by email")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok(userService.getUserByEmail(email));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update user")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable UUID id){
        try{
            user.setId(id);
            return ResponseEntity.ok(userService.updateUser(user, id));
        } catch (UserNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{username}")
    @Operation(summary = "Delete user by username")
    @PreAuthorize("hasRole('ADMIN')")
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
