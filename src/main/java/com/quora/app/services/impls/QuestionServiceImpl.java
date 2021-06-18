package com.quora.app.services.impls;

import com.quora.app.exceptions.QuestionException;
import com.quora.app.exceptions.UserException;
import com.quora.app.models.Category;
import com.quora.app.models.Question;
import com.quora.app.models.UserQuestion;
import com.quora.app.repositories.CategoryRepository;
import com.quora.app.repositories.QuestionRepository;
import com.quora.app.services.QuestionService;
import com.quora.app.services.mappers.QuestionMapper;
import static com.quora.app.utilities.ExceptionConstant.USER_DOES_NOT_EXISTS_EXCEPTION;
import static com.quora.app.utilities.ExceptionConstant.QUESTION_CONTENT_NULL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository, CategoryRepository categoryRepository) {
        this.questionRepository = questionRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    @Transactional
    public Boolean postQuestion(Question question) {
        UserQuestion userQuestion;
        Optional.ofNullable(question.getQuestionContent()).orElseThrow(() -> new QuestionException.QuestionContentNull(QUESTION_CONTENT_NULL));
        Question questionObject = QuestionMapper.mapToQuestionObject(question);
        List<Category> categoryList = QuestionMapper.mapCategoryObject(question.getCategories());
        userQuestion = QuestionMapper.mapQuestionToUserQuestion(questionObject);
        categoryRepository.saveAll(categoryList);
        questionRepository.save(userQuestion);
        return Boolean.TRUE;
    }

    @Override
    public List<Question> getAllQuestionByUserId(String userId) {
        List<UserQuestion> userQuestionList = questionRepository.findAllByUserId(userId).orElseThrow(() -> new UserException.UserNotFoundException(USER_DOES_NOT_EXISTS_EXCEPTION));

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
