package ru.blog_app.blog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.blog_app.blog.dto.request.RegisterUserRequest;
import ru.blog_app.blog.dto.response.UserDtoResponse;
import ru.blog_app.blog.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable Long id) {
        log.info("Вызов метода getUserById с параметром {}", id);
        UserDtoResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDtoResponse> register(@RequestBody RegisterUserRequest registerUserRequest) {
        log.info("Вызов метода register с параметром {}", registerUserRequest.getUsername());
        UserDtoResponse response = userService.createUser(registerUserRequest);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/put")
    public ResponseEntity<UserDtoResponse> putUser(@RequestBody UserDtoResponse userDtoResponse, @PathVariable Long id) {
        log.info("Вызов метода updateUser с параметрами {} и {}", userDtoResponse, id);
        UserDtoResponse response = userService.putUser(userDtoResponse, id);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDtoResponse> deleteUser(@PathVariable Long id) {
        log.info("Вызов метода deleteUser с параметром {}", id);
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
