package com.quora.app.services.impls;

import com.quora.app.models.User;
import com.quora.app.repositories.UserRepository;
import com.quora.app.services.UserService;
import com.quora.app.services.mappers.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Boolean saveUser(User user) {
        boolean status = Boolean.FALSE;
        Optional<User> userOptional = this.userRepository.findByUserEmail(user.getUserEmail());
        if (userOptional.isPresent()) {
            System.out.println("throw excepton");
        } else {
            this.userRepository.save(UserMapper.createUserObject(user));
            status = Boolean.TRUE;
        }
        return status;
    }

    @Override
    public User getUserByUserEmail(String userEmail) {
        return null;
    }

    @Override
    public User updateUserByUserEmail(String userEmail, User user) {
        return null;
    }

    @Override
    public Boolean deleteUserByUserEmail(String userEmail) {
        return null;
    }
}
