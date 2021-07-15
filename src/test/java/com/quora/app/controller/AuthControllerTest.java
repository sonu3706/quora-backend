package com.quora.app.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.quora.app.exceptions.AuthException;
import com.quora.app.models.ExceptionResponse;
import com.quora.app.models.JwtResponse;
import com.quora.app.models.UserAuth;
import com.quora.app.services.AuthService;
import org.junit.Assert;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

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
    public void testCreateUserAccountSuccess() throws Exception {
        UserAuth userAuth = UserAuth.builder().id(123).userEmail("abc@abc.com").userPassword("123456").build();
        when(authService.createUserAccount(any(UserAuth.class))).thenReturn(true);
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(userAuth)))
                .andExpect(status().isCreated()).andReturn();

        Assert.assertEquals(Boolean.valueOf(mvcResult.getResponse().getContentAsString()), Boolean.TRUE);
        verify(authService, times(1)).createUserAccount(any(UserAuth.class));
        verifyNoMoreInteractions(authService);
    }

    @Test()
    public void testCreateUserAccountFailure() throws Exception {
        UserAuth userAuth = UserAuth.builder().id(123).userEmail("abc@abc.com").userPassword("123456").build();
        when(authService.createUserAccount(any(UserAuth.class))).thenThrow(new AuthException.UserAlreadyExists("User Already exists in system"));
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(userAuth)))
                .andExpect(status().isConflict())
                .andReturn();
        ExceptionResponse exceptionResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ExceptionResponse.class);
        Assert.assertEquals(exceptionResponse.getExceptionMessage(), "User Already exists in system");
        verify(authService, times(1)).createUserAccount(any(UserAuth.class));
        verifyNoMoreInteractions(authService);
    }

    @Test
    public void testLoginUserSuccess() throws Exception {
        UserAuth userAuth = UserAuth.builder().id(123).userEmail("abc@abc.com").userPassword("123456").build();
        when(authService.loginUser(any(UserAuth.class))).thenReturn(JwtResponse.builder().jwtToken("603e720c30cf5f2554ddc7fd").build());
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(userAuth)))
                .andExpect(status().isOk())
                .andReturn();

        JwtResponse jwtResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), JwtResponse.class);
        Assert.assertNotNull(jwtResponse);
        Assert.assertEquals(jwtResponse.getJwtToken(), "603e720c30cf5f2554ddc7fd");
        verify(authService, times(1)).loginUser(any(UserAuth.class));
        verifyNoMoreInteractions(authService);
    }

    @Test
    public void testLoginUserFailure() throws Exception {
        UserAuth userAuth = UserAuth.builder().id(123).userEmail("abc@abc.com").userPassword("123456").build();
        when(authService.loginUser(any(UserAuth.class))).thenThrow(new AuthException.UserNotFound("User not found in system"));
        MvcResult mvcResult = mockMvc.perform(post("/api/v1/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonToString(userAuth)))
                .andExpect(status().isNotFound())
                .andReturn();

        ExceptionResponse exceptionResponse = new ObjectMapper().readValue(mvcResult.getResponse().getContentAsString(), ExceptionResponse.class);
        Assert.assertNotNull(exceptionResponse);
        Assert.assertEquals(exceptionResponse.getExceptionMessage(), "User not found in system");
        verify(authService, times(1)).loginUser(any(UserAuth.class));
        verifyNoMoreInteractions(authService);
    }
}
