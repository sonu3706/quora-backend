package com.quora.app.services.impls;

import com.quora.app.models.Question;
import com.quora.app.models.UserQuestion;
import com.quora.app.repositories.QuestionRepository;
import com.quora.app.services.QuestionService;
import com.quora.app.services.mappers.QuestionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }

    @Override
    public Boolean postQuestion(Question question) {
        UserQuestion userQuestion = null;
        if (questionRepository.existsById(question.getQuestionAskedBy())) {
            System.out.println();
        } else {
            Question questionObject = QuestionMapper.mapToQuestionObject(question);
             userQuestion = QuestionMapper.mapQuestionToUserQuestion(questionObject);
        }
        if (userQuestion != null) {
            questionRepository.save(userQuestion);
        }
        return false;
    }

    @Override
    public List<Question> getAllQuestionByUserId(String userId) {
        return null;
    }

    @Override
    public Boolean deleteQuestionByQuestionId(String questionId) {
        return null;
    }

    @Override
    public Question updateQuestionByQuestionId(String questionId, Question question) {
        return null;
    }

    @Override
    public List<Question> getAllQuestions() {
        return null;
    }

    @Override
    public Question getQuestionByQuestionId(String id) {
        return null;
    }
}
