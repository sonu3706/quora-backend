package com.quora.app.controllers;

import com.quora.app.models.Question;
import com.quora.app.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/questions")
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping()
    public ResponseEntity<?> postQuestion(@RequestBody() Question question) {
        return ResponseEntity.status(HttpStatus.CREATED).body(questionService.postQuestion(question));
    }

    @GetMapping()
    public ResponseEntity<?> getAllQuestion() {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestions());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getQuestionById(@PathVariable("id") String id) {
      return ResponseEntity.status(HttpStatus.OK).body(questionService.getQuestionByQuestionId(id));
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> updateQuestionById(@PathVariable("id") String id, @RequestBody() Question question) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.updateQuestionByQuestionId(id, question));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteQuestionById(@PathVariable("id") String id) {
       return ResponseEntity.status(HttpStatus.OK).body(questionService.deleteQuestionByQuestionId(id));
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<?> getAllQuestionByUserId(@PathVariable("userId") String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(questionService.getAllQuestionByUserId(userId));
    }
}
