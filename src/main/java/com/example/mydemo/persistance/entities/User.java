package com.example.mydemo.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "UK_email", columnNames = "email"),
        @UniqueConstraint(name = "UK_userName", columnNames = "userName")
})
public class User implements UserDetails {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private UUID id;
    @Column(unique = true)
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username length must be between 3 and 50 characters")
    private String userName;
    @NotBlank(message = "First Name is required")
    @Size(min = 3, max = 50, message = "Last Name length must be between 3 and 50 characters")
    private String firstName;
    @NotBlank(message = "Last Name is required")
    @Size(min = 3, max = 50, message = "Last Name length must be between 3 and 50 characters")
    private String lastName;
    @Column(unique = true)
    @Email(message = "Invalid email")
    @NotBlank(message = "Email is required")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password length must be grater than 8 characters")
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public String getPassword(){
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
