package com.quora.app.services;

import com.quora.app.models.JwtResponse;
import com.quora.app.models.UserAuth;

public interface AuthService {
    Boolean createUserAccount(UserAuth userAuth);

    JwtResponse loginUser(UserAuth userAuth);
}
