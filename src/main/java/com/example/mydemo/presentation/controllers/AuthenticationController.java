package com.example.mydemo.presentation.controllers;

import com.example.mydemo.application.dtos.*;
import com.example.mydemo.application.exceptions.EmailExistsException;
import com.example.mydemo.application.exceptions.UsernameExistsException;
import com.example.mydemo.application.services.AuthenticationService;
import com.example.mydemo.application.services.UserService;
import com.example.mydemo.persistance.entities.User;
import com.example.mydemo.utils.Summary;
import com.example.mydemo.utils.Tags;
import com.example.mydemo.utils.Urls;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(Urls.BASE_AUTH_URL)
@CrossOrigin(origins = Urls.ORIGINS_URL)
@Tag(name = Tags.AUTHENTICATION)
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final JavaMailSender mailSender;
    private final UserService userService;

    @Operation(summary = Summary.REGISTER)
    @PostMapping(Urls.REGISTER_URL)
    public ResponseEntity<AuthenticationResponseDTO> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        try{
            return ResponseEntity.ok(authenticationService.register(registerRequestDTO));
        } catch (UsernameExistsException | EmailExistsException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = Summary.LOGIN)
    @PostMapping(Urls.LOGIN_URL)
    public ResponseEntity<AuthenticationResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO){
        return ResponseEntity.ok(authenticationService.login(loginRequestDTO));
    }

    @PostMapping(Urls.FORGOT_PASSWORD_URL)
    @Operation(summary = Summary.FORGOT_PASSWORD)
    public ResponseEntity<Map<String,Boolean>> forgotPassword(@RequestBody @Valid PasswordResetRequestDTO requestDTO) throws MessagingException, UnsupportedEncodingException {
        Map<String,Boolean> response = new HashMap<>();

        String token = RandomStringUtils.random(10);
        userService.updateResetPasswordToken(token, requestDTO.getEmail());

        String resetPasswordLink = "http://localhost:8080"+Urls.BASE_AUTH_URL+"/reset_password?token="+token;
        sendEmail(requestDTO.getEmail(), resetPasswordLink);

        response.put("Reset password link sent to "+requestDTO.getEmail(),Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Urls.RESET_PASSWORD_URL)
    @Operation(summary = Summary.RESET_PASSWORD)
    public ResponseEntity<Map<String,Boolean>> resetPassword(@RequestParam("token") String token, @RequestBody @Valid PasswordResetDTO requestDTO){
        Map<String,Boolean> response = new HashMap<>();
        User user = userService.getByResetPasswordToken(token);
        if(user == null){
            response.put("Invalid token",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }
        /*if (StringUtils.equals(requestDTO.getNewPassword(),requestDTO.getConfirmPassword())){
            response.put("Passwords don't match",Boolean.FALSE);
            return ResponseEntity.ok(response);
        }*/
        userService.updatePassword(user, requestDTO.getNewPassword());
        response.put("Password updated successfully",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    public void sendEmail(String recipientEmail, String link) throws MessagingException, UnsupportedEncodingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom("contact@mystore.tn", "MyStore Support");
        helper.setTo(recipientEmail);

        String subject = "Here's the link to reset your password";

        String content = "<p>Hello,</p>"
                + "<p>You have requested to reset your password.</p>"
                + "<p>Click the link below to change your password:</p>"
                + "<p><a href=\"" + link + "\">Change my password</a></p>";

        helper.setSubject(subject);

        helper.setText(content, true);

        mailSender.send(message);
    }

}
