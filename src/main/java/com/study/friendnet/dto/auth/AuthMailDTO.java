package com.study.friendnet.dto.auth;

public record AuthMailDTO(
    String email,
    String subject,
    String message
) {
    
}
