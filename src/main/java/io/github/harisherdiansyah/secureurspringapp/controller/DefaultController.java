package io.github.harisherdiansyah.secureurspringapp.controller;

import io.github.harisherdiansyah.secureurspringapp.model.UserDetailsModel;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String defaultEndpoint(@AuthenticationPrincipal UserDetailsModel userDetails) {
        return "Welcome to the Secure Your Spring App!\n"
                + "Current User: " + (userDetails != null ? userDetails.getUsername() : "<empty>");
    }
}
