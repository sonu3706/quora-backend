package com.quora.app.services;

import com.quora.app.models.UserAuth;

public interface AuthService {
    Boolean createUserAccount(UserAuth userAuth);

    String loginUser(UserAuth userAuth);
}
