package com.tammam.secure_notes.security.jwt;

import java.security.Key;
import java.util.Date;
import java.util.logging.Logger;

import javax.crypto.SecretKey;

import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;

public class JwtUtils {
	
	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${spring.app.jwtSecret}")
    private String jwtSecret;

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationMs;
	

	public String getJwtFromHeader(HttpServletRequest request)
	{
      
		String bearerToken = request.getHeader("Authorization");
		if (bearerToken!=null && bearerToken.startsWith("Bearer"))
		{
			return bearerToken.substring(7);
		}
		else return null;
	}
	
    public String generateToken(UserDetails userDetails)
    {
    	String userName=  userDetails.getUsername();
    	return Jwts.builder().subject(userName).issuedAt(new Date()).expiration(new Date(new Date().getTime()+ jwtExpirationMs)).
    			signWith(key()).compact();
    }
    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
        /*Inspects the byte array length and builds a secure SecretKey instance appropriate for HMAC-SHA algorithms (like HS256)*/
    }
    private String getUserNameFromToken(String token)
    {
    	return Jwts.parser().verifyWith((SecretKey) key()).build().parseSignedClaims(token).getPayload().getSubject();
    	
    	 // Jwts.parser(): Initializes the parser builder.
    	//  verifyWith((SecretKey) key()): Sets the cryptographic key used to verify the token's signature.
    	// build() Compiles the configurations into a thread-safe JwtParser instance
    	// parseSignedClaims(token):Parses the token string and validates its integrity, expiration, and signature.
    	//getPayload(): Retrieves the JWT claims (the body payload).getSubject(): Extracts the standard sub field, which typically stores the
    }
    public boolean validateToken(String token)
    {  try {
    	System.out.println("validate");
    	Jwts.parser().verifyWith((SecretKey)key()).build().parseSignedClaims(token).getPayload().getSubject();
    	return true;
    	
    }
    catch(MalformedJwtException e)
    {
    	logger.error("Invalid jwt token " + e.getMessage());
    }
    catch(ExpiredJwtException e)
    {
    	logger.error("jwt token is expired " + e.getMessage());
    }

    catch(UnsupportedJwtException e)
    {
    	logger.error("jwt token is expired " + e.getMessage());
    }
    catch (IllegalArgumentException e) {
        logger.error("JWT claims string is empty: {}", e.getMessage());
    }
    return false;
}
}
