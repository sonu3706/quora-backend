package com.quora.app.services;

import com.quora.app.models.User;

public interface UserService {
    Boolean saveUser(User user);

    User getUserByUserEmail(String userEmail);

    User updateUserByUserEmail(String userEmail, User user);

    Boolean deleteUserByUserEmail(String userEmail);
}
