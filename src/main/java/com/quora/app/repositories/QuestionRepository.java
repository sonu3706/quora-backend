package com.quora.app.repositories;

import com.quora.app.models.UserQuestion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends MongoRepository<UserQuestion, String> {
}
