package com.examly.springapp.dto;

public class LoginResponseDto {

    private Long userId;
    private String jwtToken;
    private String username;
    private String role;
    private String email;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getJwtToken() {
        return jwtToken;
    }

    public void setJwtToken(String jwtToken) {
        this.jwtToken = jwtToken;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LoginResponseDto() {
    }

    public LoginResponseDto(Long userId, String jwtToken, String username, String role, String email) {
        this.userId = userId;
        this.jwtToken = jwtToken;
        this.username = username;
        this.role = role;
        this.email = email;
    }

    @Override
    public String toString() {
        return "LoginResponseDto [userId=" + userId + ", jwtToken=" + jwtToken + ", username=" + username + ", role="
                + role + ", email=" + email + "]";
    }
}
