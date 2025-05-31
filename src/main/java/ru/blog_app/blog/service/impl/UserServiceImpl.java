package ru.blog_app.blog.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.blog_app.blog.dto.RegisterUserRequest;
import ru.blog_app.blog.dto.UserDtoResponse;
import ru.blog_app.blog.exception.UserAlreadyExistsException;
import ru.blog_app.blog.exception.UserNotFoundException;
import ru.blog_app.blog.models.User;
import ru.blog_app.blog.repository.UserRepository;
import ru.blog_app.blog.service.UserService;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserDtoResponse getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        return createUserDtoResponse(user);
    }

    @Override
    public UserDtoResponse createUser(RegisterUserRequest request) {
        if(!userRepository.existsByUsername(request.getUsername()) &&
                !userRepository.existsByEmail(request.getEmail())) {
            User user = User.builder()
                    .username(request.getUsername())
                    .password(request.getPassword())
                    .email(request.getEmail())
                    .build();
            userRepository.save(user);
            return createUserDtoResponse(user);
        }
        else {
            throw new UserAlreadyExistsException("User already exists");
        }
    }

    @Override
    public UserDtoResponse putUser(UserDtoResponse request, Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found")
        );
        user.setUsername(request.getUsername());
//        user.setPassword(request.getPassword());
        user.setEmail(request.getEmail());
        userRepository.save(user);
        return createUserDtoResponse(user);
    }

    @Override
    public void deleteUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(String.format("Пользователь с id: %d не найден", id))
        );
        userRepository.delete(user);
    }

    private UserDtoResponse createUserDtoResponse(User user) {
        UserDtoResponse response = new UserDtoResponse();
        response.setId(user.getId());
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
//        response.setPassword(user.getPassword());
        return response;
    }
}
