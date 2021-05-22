package com.quora.app.services.mappers;

import com.quora.app.models.User;
import com.quora.app.models.UserAuth;

import java.time.LocalDate;

/**
 * Mapper class to map to desired Objects
 */
public class UserMapper {

    public static UserAuth createUserAuthObject(UserAuth userAuth) {
        userAuth.setUserActiveState(Boolean.TRUE);
        userAuth.setAccountModifiedOn(LocalDate.now());
        return userAuth;
    }

    public static User createUserObject(User user) {
        user.setUserActive(Boolean.TRUE);
        user.setUserAddedOn(LocalDate.now());
        user.setProfileModifiedOn(LocalDate.now());
        return user;
    }
}
