package by.zelezinsky.reservationsystembooking.controller.user;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.user.authentication.AuthenticationDto;
import by.zelezinsky.reservationsystembooking.dto.user.user.UserPreviewDto;
import by.zelezinsky.reservationsystembooking.service.security.JwtAuthenticationService;
import by.zelezinsky.reservationsystembooking.service.user.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;
    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping(Url.User.Authentication.BASE)
    public ResponseEntity<Map<Object, Object>> authenticate(@Valid @RequestBody AuthenticationDto request) {
        Map<Object, Object> response = jwtAuthenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping(Url.User.Registration.BASE)
    public UserPreviewDto register(@Valid @RequestBody UserPreviewDto dto) {
        dto.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));
        return userService.register(dto);
    }
}
