package by.zelezinsky.reservationsystembooking.security;

import by.zelezinsky.reservationsystembooking.entity.user.User;
import by.zelezinsky.reservationsystembooking.exception.JwtAuthenticationException;
import by.zelezinsky.reservationsystembooking.repository.PermissionRepository;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    private final UserDetailsService userDetailsService;
    private final PermissionRepository permissionRepository;
    @Value("${jwt.expiration}")
    private Long validityTime;
    @Value("${jwt.secret}")
    private String secretKey;
    @Value("${jwt.header}")
    private String httpHeader;
    private Key key;

    public JwtTokenProvider(@Qualifier("userService") UserDetailsService userDetailsService,
                            @Autowired PermissionRepository permissionRepository) {
        this.userDetailsService = userDetailsService;
        this.permissionRepository = permissionRepository;
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        List<SimpleGrantedAuthority> authorities = permissionRepository.findAllByRole(user.getRole())
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getName()))
                .collect(Collectors.toList());
        claims.put("authorities", authorities);
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityTime * 1000);
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
        this.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
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
