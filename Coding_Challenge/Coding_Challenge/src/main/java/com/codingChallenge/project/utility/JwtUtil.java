package com.codingChallenge.project.utility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.Key;
import java.util.Date;
import java.util.Properties;

@Component
public class JwtUtil {
    //Secret Key
    private static final String secretKey = "Coding_Challenge_2972003P20O2K2O9e2973M00N";
    //Expiration time in milliseconds
    private static final long expirationTimeInMills = 43200000;

    //Building Signing key from secret key
    private Key getSigningKey(){
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    /*
    Create a JWT Token with:
    1. Subject = username/email
    2. Creation time: new Date().now()
    3. Expiration time: now + expirationTimeInMills
     */
    public String createToken(String username){
        return Jwts.builder().setSubject(username) //setting username
                .setIssuedAt(new Date(System.currentTimeMillis())) //setting current time
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeInMills)) //setting Expiration time
                .signWith(getSigningKey(), SignatureAlgorithm.HS256) //signing the token with HMCA256 Algorithm and secret key
                .compact();
    }

    /*
    Extract the username from the token's subject
     */
    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())    // use the same key that signed it
                .build()
                .parseClaimsJws(token)             // parse & verify signature
                .getBody()
                .getSubject();                     // return the “sub” claim
    }

    /*
    Verify that token matches the username and the token is not expired
     */
    public boolean verifyToken(String token, String email){
        //Extracting the subject
        String extractedEmail = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        // b) Extract “exp” from token
        Date expirationDate = Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration();

        // c) Check that the extracted subject equals the email we expect, and token is still valid
        return extractedEmail.equals(email)
                && new Date().before(expirationDate);
    }

}