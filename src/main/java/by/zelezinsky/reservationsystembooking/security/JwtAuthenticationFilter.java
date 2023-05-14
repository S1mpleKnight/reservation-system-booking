package by.zelezinsky.reservationsystembooking.security;

import by.zelezinsky.reservationsystembooking.exception.JwtAuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtTokenProvider tokenProvider;

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
    ) throws ServletException, IOException {
        String token = tokenProvider.resolveToken(httpServletRequest);
        try {
            acceptAuthentication(token);
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (JwtAuthenticationException e) {
            rejectAuthentication(e);
        }
    }

    private void rejectAuthentication(JwtAuthenticationException e) {
        log.error(e.getMessage());
        SecurityContextHolder.clearContext();
        throw new JwtAuthenticationException(e.getMessage());
    }

    private void acceptAuthentication(String token) {
        if (isTokenValid(token)) {
            Authentication authentication = tokenProvider.getAuthentication(token);
            if (authentication != null) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
    }

    private boolean isTokenValid(String token) {
        return token != null && tokenProvider.validateToken(token);
    }
}
