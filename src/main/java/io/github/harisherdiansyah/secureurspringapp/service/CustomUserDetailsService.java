package io.github.harisherdiansyah.secureurspringapp.service;

import io.github.harisherdiansyah.secureurspringapp.model.Role;
import io.github.harisherdiansyah.secureurspringapp.model.User;
import io.github.harisherdiansyah.secureurspringapp.model.UserDetailsModel;
import io.github.harisherdiansyah.secureurspringapp.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // treat username as email
        User user = userRepository.findUserByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("Email is not registered");
        }

        List<Role> userRole = List.of(user.getRole());

        return new UserDetailsModel(
                user.getEmail(),
                user.getPassword(),
                userRole.stream()
                        .map(role -> new SimpleGrantedAuthority(role.toString()))
                        .collect(Collectors.toList())
        );
    }
}
