package com.quora.app.service;

import com.quora.app.exceptions.AuthException;
import com.quora.app.models.UserAuth;
import com.quora.app.repositories.AuthRepository;
import com.quora.app.services.AuthService;
import com.quora.app.services.TokenService;
import com.quora.app.services.impls.AuthServiceImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    public AuthService authService;
    @Mock
    private AuthRepository authRepository;
    @Mock
    private TokenService tokenService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        authService = new AuthServiceImpl(authRepository, tokenService);
    }

    @After
    public void tearDown() {
        authRepository = null;
        tokenService = null;
        authService = null;
    }

    @Test
    public void testCreateUserAccountSuccess() {
        when(authRepository.findUserAuthsByUserEmail("chandan@abc.com")).thenReturn(Optional.empty());
        Boolean status = authService.createUserAccount(createUserAuthObject());
        Assert.assertNotNull(status);
        Assert.assertTrue(status);

        verify(authRepository, times(1)).findUserAuthsByUserEmail("chandan@abc.com");
        verify(authRepository, times(1)).save(any());
        verifyNoMoreInteractions(authRepository);
    }

    @Test(expected = AuthException.UserAlreadyExists.class)
    public void testCreateUserAccountFailure() {
        when(authRepository.findUserAuthsByUserEmail("chandan@abc.com")).thenReturn(Optional.ofNullable(createUserAuthObject()));
        authService.createUserAccount(createUserAuthObject());
    }

    @Test
    public void testLoginUserSuccess() {

    }

    @Test
    public void testLoginUserFailure() {

    }

    private UserAuth createUserAuthObject() {
        return UserAuth.builder().userEmail("chandan@abc.com").userPassword("12345678").build();
    }
}
