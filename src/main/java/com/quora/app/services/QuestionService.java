package com.quora.app.services;

import com.quora.app.models.Question;

import java.util.List;

public interface QuestionService {

    List<Question> getAllQuestions();

    Boolean postQuestion(Question question);

    Question getQuestionByQuestionId(String id);

    List<Question> getAllQuestionByUserId(String userId);

    Boolean deleteQuestionByQuestionId(String questionId);

    Question updateQuestionByQuestionId(String questionId, Question question);
}
