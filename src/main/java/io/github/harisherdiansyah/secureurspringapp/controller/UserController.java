package io.github.harisherdiansyah.secureurspringapp.controller;

import io.github.harisherdiansyah.secureurspringapp.dto.LoginDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.UserRequestDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.UserResponseDTO;
import io.github.harisherdiansyah.secureurspringapp.model.UserDetailsModel;
import io.github.harisherdiansyah.secureurspringapp.service.JwtService;
import io.github.harisherdiansyah.secureurspringapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public UserResponseDTO register(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        return userService.register(userRequestDTO);
    }

    @PostMapping("/login")
    public LoginDTO.Response login(@Valid @RequestBody LoginDTO.Request loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.email(), loginRequest.password())
        );
        UserDetailsModel userDetails = (UserDetailsModel) authentication.getPrincipal();
        String token = jwtService.generateToken(userDetails);
        return new LoginDTO.Response(userDetails.getUsername(), token);
    }
}
