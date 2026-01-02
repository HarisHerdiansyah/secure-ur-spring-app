package io.github.harisherdiansyah.secureurspringapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultController {
    @GetMapping("/")
    public String defaultEndpoint() {
        return "Welcome to the Secure Your Spring App!";
    }
}
