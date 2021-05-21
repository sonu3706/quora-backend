package com.quora.app.services.mappers;

import com.quora.app.models.Question;
import com.quora.app.models.UserQuestion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class QuestionMapper {

    public static Question mapToQuestionObject(Question question) {
        Question questionObject = new Question();
        questionObject.setQuestionId(UUID.randomUUID().toString());
        questionObject.setQuestionType(question.getQuestionType());
        questionObject.setQuestionContent(question.getQuestionContent());
        questionObject.setQuestionAskedBy(question.getQuestionAskedBy());
        questionObject.setQuestionActiveState(Boolean.TRUE);
        questionObject.setQuestionAddedOn(LocalDate.now());
        return questionObject;
    }

    public static UserQuestion mapQuestionToUserQuestion(Question question) {
        UserQuestion userQuestion = new UserQuestion();
        List<Question> questionList = new ArrayList<>();
        questionList.add(question);
        userQuestion.setUserId(question.getQuestionAskedBy());
        userQuestion.setQuestions(questionList);
        return userQuestion;
    }
}
