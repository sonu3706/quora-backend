package com.quora.app.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quora.app.models.Question;
import com.quora.app.services.QuestionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionService mockQuestionService;

    // Parsing String format data into JSON format
    private static String jsonToString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void testPostQuestionSuccess() throws Exception {
        when(mockQuestionService.postQuestion(any(Question.class))).thenReturn(Boolean.TRUE);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/questions/post")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(Question.class)))
                .andExpect(status().isOk())
                .andReturn();
    }
}
