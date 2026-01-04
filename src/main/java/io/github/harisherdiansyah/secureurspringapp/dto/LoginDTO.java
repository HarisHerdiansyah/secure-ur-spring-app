package io.github.harisherdiansyah.secureurspringapp.dto;

import jakarta.validation.constraints.NotBlank;

public class LoginDTO {
    public record Request(@NotBlank(message = "Email is required") String email,
                          @NotBlank(message = "Password is required") String password) {
    }

    public record Response(String username, String token) {}
}
