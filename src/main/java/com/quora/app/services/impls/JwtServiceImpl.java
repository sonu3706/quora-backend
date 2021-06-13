package com.quora.app.services.impls;

import com.quora.app.models.JwtResponse;
import com.quora.app.services.TokenService;
import com.quora.app.utilities.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JwtServiceImpl implements TokenService {

    private final JwtUtil jwtUtil;

    @Autowired
    public JwtServiceImpl(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public JwtResponse generateToken(String userEmail) {
        String token = Optional.ofNullable(jwtUtil.generateToken(userEmail)).orElse(null);
        return JwtResponse.builder().jwtToken(token).build();
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
