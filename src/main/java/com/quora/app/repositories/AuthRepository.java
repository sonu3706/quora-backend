package com.quora.app.repositories;

import com.quora.app.models.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthRepository extends JpaRepository<UserAuth, Integer> {
    public Optional<UserAuth> findUserAuthsByUserEmail(String userEmail);
    Optional<UserAuth> findUserAuthsByUserEmailAndUserPassword(String userEmail, String userPassword);
}
