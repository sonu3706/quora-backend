package com.quora.app.services.impls;

import com.quora.app.exceptions.AuthException;
import com.quora.app.exceptions.TokenException;
import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import com.quora.app.services.AuthService;
import com.quora.app.services.TokenService;
import com.quora.app.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

import static com.quora.app.utilities.ExceptionConstant.UNABLE_TO_GENERATE_TOKEN;
import static com.quora.app.utilities.ExceptionConstant.USER_DOES_EXISTS_EXCEPTION;


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
        Map<String, String> tokenMap = null;
        UserAuth userAuthObject = this.authRepository.findUserAuthsByUserEmailAndUserPassword(userAuth.getUserEmail(), userAuth.getUserPassword())
                .orElseThrow(() -> new AuthException.UserNotFound(USER_DOES_EXISTS_EXCEPTION));
        if (userAuthObject.getUserActiveState() && userAuthObject.getUserPassword().equals(userAuth.getUserPassword())) {
            /*Generate JWT token*/
            tokenMap = jwtTokenService.generateToken(userAuthObject.getUserEmail());
            Optional.ofNullable(tokenMap.get("token")).orElseThrow(() -> new TokenException.UnableToGenerateToken(UNABLE_TO_GENERATE_TOKEN));
        } else {
            /* Throw Exception of password mismatch exception */
            System.out.println("Password did not match");
        }
        return tokenMap;
    }
}
