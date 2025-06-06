package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.enums.Role;
import ru.blog_app.blog.models.User;
import ru.blog_app.blog.repository.UserRepository;
import ru.blog_app.blog.security.JwtTokenProvider;
import ru.blog_app.blog.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void register(AuthRequest request){
        if(userRepository.existsByEmail(request.getEmail()) || userRepository.existsByUsername(request.getUsername())){
            throw new RuntimeException("Пользователь с таким email или именем уже существует");
        }
        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
    }

    public String login(AuthRequest request){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        return jwtTokenProvider.generateToken(authentication);
    }
}
