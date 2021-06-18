package com.quora.app.repositories;

import com.quora.app.models.UserQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends MongoRepository<UserQuestion, String> {

    Optional<UserQuestion> findByUserId(String userId);

    Optional<List<UserQuestion>> findAllByUserId(String userId);
}
