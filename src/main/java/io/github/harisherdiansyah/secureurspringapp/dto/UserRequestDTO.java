package io.github.harisherdiansyah.secureurspringapp.dto;

import io.github.harisherdiansyah.secureurspringapp.model.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Data
@RequiredArgsConstructor
public class UserRequestDTO {
    @NotBlank(message = "Email is required")
    private final String email;

    @NotBlank(message = "Password is required")
    @Size(
            min = 8,
            max = 16,
            message = "Password must be at least 8 characters and " +
                    "cannot over than 16 characters"
    )
    private final String password;

    @NotBlank(message = "Full name is required")
    @Size(
            min = 3,
            message = "Full name must be at least 3 characters"
    )
    private final String fullName;

    private Role role;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
