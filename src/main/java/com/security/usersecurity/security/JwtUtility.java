package com.security.usersecurity.security;

import com.security.usersecurity.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtility implements Serializable {

    @Serial
    private static final long serialVersionUID = 7131602650216589996L;
    public final String secretKey = "adlfjnvoiaewejofdclldjflavmalldsjfldjf";
    public static final Date exp = new Date(System.currentTimeMillis()+3600000);
    @Autowired
    UserService userService;

    public Claims getAllExtractClaims(String token){
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimResolver) {
        try {
            Claims claims = getAllExtractClaims(token);
            return claimResolver.apply(claims);
        } catch (Exception e) {
            // Handle exceptions (e.g., invalid token, expired token)
            throw new RuntimeException("Invalid token", e);
        }
    }

    public String getExtractUserName(String token){
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationFromToken(String token){
        return getAllExtractClaims(token).getExpiration();
    }

    public boolean isTokenExpiration(String token){
        return getExpirationFromToken(token).after(new Date(System.currentTimeMillis()));
    }

    public String generateToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", userDetails.getAuthorities());
        return doGenerateToken(claims,userDetails.getUsername());
    }

    public String doGenerateToken(Map<String, Object> claims, String subject){
        return Jwts.builder()
                .setSubject(subject)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(exp)
                .compact();
    }

    public Boolean validToken(String token, UserDetails userDetails){
        try {
            String username = getExtractUserName(token);
            if(null!=username && username.equals(userDetails.getUsername())){
                return isTokenExpiration(token);
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

}
