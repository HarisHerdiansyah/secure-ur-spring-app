package io.github.harisherdiansyah.secureurspringapp.service;

import io.github.harisherdiansyah.secureurspringapp.dto.UserRequestDTO;
import io.github.harisherdiansyah.secureurspringapp.dto.UserResponseDTO;
import io.github.harisherdiansyah.secureurspringapp.model.User;
import io.github.harisherdiansyah.secureurspringapp.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder(12);

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO register(UserRequestDTO userRequestDTO) {
        User.UserBuilder userBuilder = User.builder()
                .email(userRequestDTO.getEmail())
                .password(bcrypt.encode(userRequestDTO.getPassword()))
                .fullName(userRequestDTO.getFullName())
                .role(userRequestDTO.getRole());
        User newUser = userRepository.save(userBuilder.build());
        return new UserResponseDTO(
                newUser.getId(),
                newUser.getEmail(),
                newUser.getFullName(),
                newUser.getRole(),
                newUser.getCreatedAt(),
                newUser.getUpdatedAt()
        );
    }
}
