package com.quora.app.services;

import java.util.Map;

public interface TokenService {
    Map<String, String> generateToken(String userEmail);

    Boolean validateToken(String token);

    Boolean isTokenExpired(String token);
}
