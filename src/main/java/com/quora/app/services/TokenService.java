package com.quora.app.services;

import com.quora.app.models.JwtResponse;

import java.util.Map;

public interface TokenService {
    JwtResponse generateToken(String userEmail);

    Boolean validateToken(String token);

    Boolean isTokenExpired(String token);
}
