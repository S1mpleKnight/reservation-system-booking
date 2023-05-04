package by.zelezinsky.reservationsystembooking.controller.user;

import by.zelezinsky.reservationsystembooking.controller.Url;
import by.zelezinsky.reservationsystembooking.dto.user.authentication.AuthenticationDto;
import by.zelezinsky.reservationsystembooking.service.security.JwtAuthenticationService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(Url.User.Authentication.BASE)
public class AuthenticationController {

    private final JwtAuthenticationService jwtAuthenticationService;

    @PostMapping
    public ResponseEntity<Map<Object, Object>> authenticate(@Valid @RequestBody AuthenticationDto request) {
        Map<Object, Object> response = jwtAuthenticationService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
