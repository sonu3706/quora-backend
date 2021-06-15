package com.quora.app.services.impls;

import com.quora.app.exceptions.AuthException;
import com.quora.app.exceptions.TokenException;
import com.quora.app.models.JwtResponse;
import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import com.quora.app.services.AuthService;
import com.quora.app.services.TokenService;
import com.quora.app.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

import static com.quora.app.utilities.ExceptionConstant.*;


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
        Optional<UserAuth> userAuthOptional = this.authRepository.findUserAuthsByUserEmail(userAuth.getUserEmail());
        if (userAuthOptional.isPresent()) {
            throw new AuthException.UserAlreadyExists(USER_ALREADY_EXISTS + "with id:- " + userAuth.getUserEmail());
        } else {
            authRepository.save(UserMapper.createUserAuthObject(userAuth));
        }
        return Boolean.TRUE;
    }

    @Override
    public JwtResponse loginUser(UserAuth userAuth) {
        JwtResponse jwtResponse = null;
        UserAuth userAuthObject = this.authRepository.findUserAuthsByUserEmailAndUserPassword(userAuth.getUserEmail(), userAuth.getUserPassword())
                .orElseThrow(() -> new AuthException.UserNotFound(USER_DOES_NOT_EXISTS_EXCEPTION  + userAuth.getUserEmail()));
        if (userAuthObject.getUserActiveState() && userAuthObject.getUserPassword().equals(userAuth.getUserPassword())) {
            /*Generate JWT token*/
            jwtResponse = jwtTokenService.generateToken(userAuthObject.getUserEmail());
            Optional.ofNullable(jwtResponse.getJwtToken()).orElseThrow(() -> new TokenException.UnableToGenerateToken(UNABLE_TO_GENERATE_TOKEN));
        } else {
            /* Throw Exception of password mismatch exception */
           throw new AuthException.PasswordMisMatch(USERID_PASSWORD_MISMATCH);
        }
        return jwtResponse;
    }

    @Override
    public Boolean deleteUserAccount(String userEmailId) {
        UserAuth userAuth = authRepository.findUserAuthsByUserEmail(userEmailId)
                .orElseThrow(() -> new AuthException.UserNotFound(USER_DOES_NOT_EXISTS_EXCEPTION + userEmailId));
        authRepository.delete(userAuth);
        return Boolean.TRUE;
    }

    @Override
    public Boolean changeUserPassword(UserAuth userAuth) {
        UserAuth userAuthObject = authRepository.findUserAuthsByUserEmail(userAuth.getUserEmail())
                .orElseThrow(() -> new AuthException.UserNotFound(USER_DOES_NOT_EXISTS_EXCEPTION + userAuth.getUserEmail()));
        if (userAuthObject.getUserPassword().equalsIgnoreCase(userAuth.getUserPassword())) {
            throw new AuthException.PasswordSame(PASSWORD_CHARACTER_SAME);
        } else {
            userAuthObject.setUserPassword(userAuth.getUserPassword());
            userAuthObject.setAccountModifiedOn(LocalDate.now());
            authRepository.save(userAuthObject);
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deactivateUserAccount(UserAuth userAuth) {
        return null;
    }
}
