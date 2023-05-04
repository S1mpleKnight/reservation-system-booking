package by.zelezinsky.reservationsystembooking.service.security;

import by.zelezinsky.reservationsystembooking.dto.user.authentication.AuthenticationDto;

import java.util.Map;

public interface JwtAuthenticationService {

    Map<Object, Object> authenticate(AuthenticationDto request);
}
