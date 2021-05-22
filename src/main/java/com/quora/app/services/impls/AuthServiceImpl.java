package com.quora.app.services.impls;

import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import com.quora.app.services.AuthService;
import com.quora.app.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final AuthRepository authRepository;

    @Autowired
    public AuthServiceImpl(AuthRepository authRepository) {
        this.authRepository = authRepository;
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
    public String loginUser(UserAuth userAuth) {
        return null;
    }
}
