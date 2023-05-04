package by.zelezinsky.reservationsystembooking.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

@Component
@Slf4j
public class FilterChainExceptionHandler extends OncePerRequestFilter {
    private final HandlerExceptionResolver resolver;

    @Autowired
    public FilterChainExceptionHandler(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver resolver) {
        this.resolver = resolver;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        } catch (Exception e) {
            log.error(e.getMessage());
            resolver.resolveException(httpServletRequest, httpServletResponse, null, e);
        }
    }
}
