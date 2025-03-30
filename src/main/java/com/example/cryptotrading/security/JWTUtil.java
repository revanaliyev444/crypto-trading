package com.example.cryptotrading.security;

import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JWTUtil {

    private static final Key SecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private static final long ExpirationTime = 86400000;

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + ExpirationTime))
                .signWith(SecretKey)
                .compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    public String extractUsername(String token){
      return extractClaim(token, Claims::getSubject);
    }

    public String extractClaim(String token, Function<Claims, String> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String extractExpiration(String token) {
        return extractClaim(token, claims -> String.valueOf(claims.getExpiration()));
    }

    public String extractAllClaims(String token, Function< Claims, Object> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims).toString();
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(SecretKey)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

}