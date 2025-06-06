package ru.blog_app.blog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.service.AuthService;
import ru.blog_app.blog.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        log.info("Вызов метода аутентификации пользователя для имени пользователя {}",request.getUsername());
        String response = authService.login(request);
        log.info("Пользователь аутентифицирован успешно");
        return ResponseEntity.ok(Map.of("token", response));
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest request) {
        log.info("Вызов метода регистрации пользователя для имени пользователя {}",request.getUsername());
        authService.register(request);
        log.info("Пользователь зарегистрирован успешно");
        return ResponseEntity.ok("User registered successfully");
    }
}