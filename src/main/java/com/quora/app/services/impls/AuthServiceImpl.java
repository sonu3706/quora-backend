package com.quora.app.services.impls;

import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import com.quora.app.services.AuthService;
import com.quora.app.services.TokenService;
import com.quora.app.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;
    private final TokenService jwtTokenService;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository, TokenService jwtTokenService) {
        this.authRepository = authRepository;
        this.jwtTokenService = jwtTokenService;
    }

    @Override
    public Boolean createUserAccount(UserAuth userAuth) {
        boolean status = Boolean.FALSE;
        Optional<UserAuth> userAuthOptional = this.authRepository.findUserAuthsByUserEmail(userAuth.getUserEmail());
        if (userAuthOptional.isPresent()) {
            System.out.println("Throw Exception");
        } else {
            authRepository.save(UserMapper.createUserAuthObject(userAuth));
            status = Boolean.TRUE;
        }
        return status;
    }

    @Override
    public Map<String, String> loginUser(UserAuth userAuth) {
        Map<String, String> token = null;
        Optional<UserAuth> userAuthOptional = this.authRepository.findUserAuthsByUserEmailAndUserPassword(userAuth.getUserEmail(), userAuth.getUserPassword());
        if (userAuthOptional.isPresent()) {
            UserAuth userAuthObject = userAuthOptional.get();
            if (userAuthObject.getUserActiveState() && userAuthObject.getUserPassword().equals(userAuth.getUserPassword())) {
                /*Generate JWT token*/
             token =  jwtTokenService.generateToken(userAuthObject.getUserEmail());
            } else {
                System.out.println("Password did not match");
            }
        } else {
            System.out.println("Throw exception user not present");
        }
        return token;
    }
}
