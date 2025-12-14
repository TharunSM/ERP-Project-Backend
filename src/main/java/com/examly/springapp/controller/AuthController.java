package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.examly.springapp.configuration.JwtUtils;
import com.examly.springapp.dto.LoginRequestDto;
import com.examly.springapp.dto.LoginResponseDto;
import com.examly.springapp.exception.UserAlreadyExistsException;
import com.examly.springapp.exception.UserNotFoundException;
import com.examly.springapp.model.User;
import com.examly.springapp.service.FeedbackService;
import com.examly.springapp.service.UserService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtils jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/register")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(userService.registerUser(user));
        } catch (UserAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("User Already exists");
        }
    }

    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public LoginResponseDto AuthenticateAndGetToken(@RequestBody LoginRequestDto user) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        if (authentication.isAuthenticated()) {
            User responseUser = userService.getUser(user.getUsername());
            LoginResponseDto response = new LoginResponseDto();
            response.setJwtToken(jwtService.GenerateToken(user.getUsername()));
            response.setUserId(responseUser.getUserId());
            response.setUsername(responseUser.getUsername());
            response.setEmail(responseUser.getEmail());
            response.setRole(responseUser.getRole());
            return response;
        } else {
            throw new UsernameNotFoundException("Invalid user request !!");
        }

    }

    @GetMapping("/home/feedback")
    @PreAuthorize("permitAll()")
    public ResponseEntity<?> getAllFeedbacksforHomePage() {
        return ResponseEntity.status(200).body(feedbackService.getAllFeedback());
    }

    @GetMapping("/getUser/{id}")
    @PreAuthorize("hasAnyAuthority('Manager','Employee')")
    public ResponseEntity<?> getUserObjectForTestPurpose(@PathVariable Long id) {
        return ResponseEntity.status(200).body(userService.getUserObjectById(id));
    }

}
