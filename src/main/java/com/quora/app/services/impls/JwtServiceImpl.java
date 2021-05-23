package com.quora.app.services.impls;

import com.quora.app.services.TokenService;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class JwtServiceImpl implements TokenService {
    @Override
    public Map<String, String> generateToken(String userEmail) {
        return null;
    }

    @Override
    public Boolean validateToken(String token) {
        return null;
    }

    @Override
    public Boolean isTokenExpired(String token) {
        return null;
    }
}
