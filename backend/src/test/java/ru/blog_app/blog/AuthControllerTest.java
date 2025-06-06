package ru.blog_app.blog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.blog_app.blog.controller.AuthController;
import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.service.AuthService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private AuthRequest authRequest;

    @BeforeEach
    public void setUp() {
        authRequest = new AuthRequest();
        authRequest.setUsername("testUser");
        authRequest.setPassword("testPassword");
    }

    @Test
    public void testLogin() {
        String token = "testToken";
        when(authService.login(authRequest)).thenReturn(token);

        ResponseEntity<?> responseEntity = authController.login(authRequest);

        assertEquals(ResponseEntity.ok(Map.of("token", token)), responseEntity);
        verify(authService, times(1)).login(authRequest);
    }

    @Test
    public void testRegister() {
        ResponseEntity<String> responseEntity = authController.register(authRequest);

        assertEquals(ResponseEntity.ok("User registered successfully"), responseEntity);
        verify(authService, times(1)).register(authRequest);
    }
}
