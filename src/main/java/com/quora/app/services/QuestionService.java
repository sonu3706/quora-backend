package com.quora.app.services;

import com.quora.app.models.Question;

import java.util.List;

public interface QuestionService {
    public Boolean postQuestion(Question question);

    public List<Question> getAllQuestionByUserId(String userId);

    public Boolean deleteQuestionByQuestionId(String questionId);

    public Question updateQuestionByQuestionId(String questionId, Question question);
}
