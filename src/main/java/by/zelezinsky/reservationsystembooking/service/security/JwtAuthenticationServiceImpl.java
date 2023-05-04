package by.zelezinsky.reservationsystembooking.service.security;

import by.zelezinsky.reservationsystembooking.dto.user.authentication.AuthenticationDto;
import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.NotFoundException;
import by.zelezinsky.reservationsystembooking.repository.UserRepository;
import by.zelezinsky.reservationsystembooking.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JwtAuthenticationServiceImpl implements JwtAuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    @Override
    public Map<Object, Object> authenticate(AuthenticationDto request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        return fillMap(request, createToken(request));
    }

    private String createToken(AuthenticationDto request) {
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if (user.isEmpty()) {
            throw new NotFoundException(String.format("User by %s was not found", request.getUsername()));
        }
        return tokenProvider.createToken(user.get());
    }

    private Map<Object, Object> fillMap(AuthenticationDto request, String token) {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", request.getUsername());
        response.put("token", token);
        return response;
    }
}
