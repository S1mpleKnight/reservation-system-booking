package by.zelezinsky.reservationsystembooking.security;

import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.JwtAuthenticationException;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.inject.Qualifier;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    @Value("${jwt.expiration}")
    private Long validityTime;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.header}")
    private String httpHeader;
    private SecretKey key;

    public JwtTokenProvider(@Qualifier("UserService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("authorities", user.getAuthorities());
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityTime);
        return buildToken(claims, now, validity);
    }

    public boolean validateToken(String token) {
        try {
            Jws<Claims> claimsJws = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return isTokenValid(claimsJws);
        } catch (JwtException | IllegalArgumentException e) {
            log.error(e.getMessage());
            throw new JwtAuthenticationException("JWT token is not valid");
        }
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader(httpHeader);
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = this.userDetailsService.loadUserByUsername(getUsername(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    @PostConstruct
    protected void init() {
        this.secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
        byte[] decode = Base64.getDecoder().decode(secretKey);
        this.key = Keys.hmacShaKeyFor(decode);
    }

    private String getUsername(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }

    private boolean isTokenValid(Jws<Claims> claimsJws) {
        return !claimsJws.getBody().getExpiration().before(new Date());
    }

    private String buildToken(Claims claims, Date now, Date validity) {
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
