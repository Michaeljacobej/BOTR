package com.sysem.BOTR.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private static final SecureRandom random = new SecureRandom();
    private static final byte[] keyBytes = new byte[32];

    static {
        random.nextBytes(keyBytes);
    }

    private static final String SECRET_KEY = Base64.getEncoder().encodeToString(keyBytes);

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(Keys.hmacShaKeyFor(Base64.getDecoder().decode(SECRET_KEY)), SignatureAlgorithm.HS256)
                .compact();
    }

}
