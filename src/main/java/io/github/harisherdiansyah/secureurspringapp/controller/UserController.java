package io.github.harisherdiansyah.secureurspringapp.controller;

import io.github.harisherdiansyah.secureurspringapp.dto.UserRequestDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.UserResponseDTO;
import io.github.harisherdiansyah.secureurspringapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }
}
