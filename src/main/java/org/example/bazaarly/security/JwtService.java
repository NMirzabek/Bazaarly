package org.example.bazaarly.security;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.example.bazaarly.entity.Role;
import org.example.bazaarly.entity.User;
import org.example.bazaarly.repo.UserRepository;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtService {
    private final UserRepository userRepository;

    public String generateToken(String email) {
        User user = userRepository.findByEmail(email);
        return "Bearer " + Jwts.builder()
                .subject(email)
                .claim("phone", user.getEmail())
                .claim("id", user.getId())
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))
                .signWith(getSecretKey())
                .compact();
    }

    public String generateRefreshToken(String email) {
        User user = userRepository.findByEmail(email);
        return "Bearer " + Jwts.builder()
                .subject(email)
                .claim("phone", user.getEmail())
                .claim("id", user.getId())
                .claim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 7))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(getSecretKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            throw e;
        }
    }

    public SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(
                "s9UqY3KdPbZT7v0XLEmRcfWHN2oJlxB8gMQdVSAi".getBytes()
        );
    }

    public User getUserObject(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        String email = claims.getSubject();
        String roles = (String) claims.get("roles");
        List<Role> authorities = Arrays.stream(roles.split(",")).map(Role::new).toList();
        return new User(
                email,
                authorities
        );
    }
}
