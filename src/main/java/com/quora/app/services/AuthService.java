package com.quora.app.services;

import com.quora.app.models.UserAuth;

import java.util.Map;

public interface AuthService {
    Boolean createUserAccount(UserAuth userAuth);

    Map<String, String> loginUser(UserAuth userAuth);
}
