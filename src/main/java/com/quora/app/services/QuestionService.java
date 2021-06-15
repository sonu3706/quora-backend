package com.quora.app.services;

import com.quora.app.models.Question;

import java.util.List;

public interface QuestionService {

    public List<Question> getAllQuestions();

    public Boolean postQuestion(Question question);

    public Question getQuestionByQuestionId(String id);

    public List<Question> getAllQuestionByUserId(String userId);

    public Boolean deleteQuestionByQuestionId(String questionId);

    public Question updateQuestionByQuestionId(String questionId, Question question);
}
