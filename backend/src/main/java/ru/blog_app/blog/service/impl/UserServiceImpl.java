package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.dto.response.UserDtoResponse;
import ru.blog_app.blog.enums.Role;
import ru.blog_app.blog.exception.UserAlreadyExistsException;
import ru.blog_app.blog.exception.UserNotFoundException;
import ru.blog_app.blog.models.User;
import ru.blog_app.blog.repository.UserRepository;
import ru.blog_app.blog.service.UserService;

import java.awt.*;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDtoResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        return createUserDtoResponse(user);
    }

//    @Override
//    public UserDtoResponse putUser(AuthRequest request, Long id, String currentUsername) {
//        User user = userRepository.findById(id).orElseThrow(
//                () -> new UserNotFoundException("User not found")
//        );
//        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
//        user.setEmail(request.getEmail());
//        userRepository.save(user);
//        return createUserDtoResponse(user);
//    }

    @Override
    public void deleteUser(Long id, String currentUsername) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("Пользователь с id: %d не найден", id))
        );
        if (!user.getUsername().equals(currentUsername)){
            throw new RuntimeException("Forbidden");
        }
        userRepository.delete(user);
    }

    private UserDtoResponse createUserDtoResponse(User user) {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setCreatedAt(user.getCreatedAt());
        return response;
    }
}
