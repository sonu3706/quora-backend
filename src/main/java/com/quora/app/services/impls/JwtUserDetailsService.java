package com.quora.app.services.impls;

import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final AuthRepository authRepository;

    @Autowired
    public JwtUserDetailsService(AuthRepository authRepository) {
        this.authRepository = authRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserAuth userAuth = authRepository.findUserAuthsByUserEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new User(userAuth.getUserEmail(), userAuth.getUserPassword(), new ArrayList<>());
    }
}
