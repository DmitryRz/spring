package ru.blog_app.blog.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.blog_app.blog.dto.request.AuthRequest;
import ru.blog_app.blog.dto.response.UserDtoResponse;
import ru.blog_app.blog.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDtoResponse> getUserById(@PathVariable("id") Long id) {
        log.info("Вызов метода getUserById с параметром {}", id);
        UserDtoResponse response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    /*@PostMapping("/register")
    public ResponseEntity<UserDtoResponse> register(@RequestBody AuthRequest request) {
        log.info("Вызов метода register с параметром {}", request.getUsername());
        UserDtoResponse response = userService.createUser(request);
        return ResponseEntity.ok(response);
    }*/

//    @PutMapping("/{id}/put")
//    public ResponseEntity<UserDtoResponse> putUser(@RequestBody AuthRequest request, @PathVariable Long id, Authentication authentication) {
//        log.info("Вызов метода updateUser с параметрами {} и {}", request, id);
//        UserDtoResponse response = userService.putUser(request, id, authentication.getName());
//        return ResponseEntity.ok(response);
//    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<UserDtoResponse> deleteUser(@PathVariable("id") Long id, Authentication authentication) {
        log.info("Вызов метода deleteUser с параметром {}", id);
        userService.deleteUser(id, authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
