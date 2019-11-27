package com.cdspool.main.shared;

import java.security.Key;
import java.security.SecureRandom;
import java.util.Date;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.cdspool.main.security.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class Utils {
		
		private final Random RANDOM = new SecureRandom();
	    private final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		
	    public String generateUserId(int length) {
	        return generateRandomString(length);
	    }
	    
	    public String generateAddressId(int length) {
	        return generateRandomString(length);
	    }
	    
	    private String generateRandomString(int length) {
	        StringBuilder returnValue = new StringBuilder(length);

	        for (int i = 0; i < length; i++) {
	            returnValue.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
	        }

	        return new String(returnValue);
	    }
	    
		public static boolean hasTokenExpired(String token) {
			boolean returnValue = false;

			try {
				Claims claims = Jwts.parser().setSigningKey(SecurityConstants.getTokenSecret()).parseClaimsJws(token)
						.getBody();

				Date tokenExpirationDate = claims.getExpiration();
				Date todayDate = new Date();

				returnValue = tokenExpirationDate.before(todayDate);
			} catch (ExpiredJwtException ex) {
				returnValue = true;
			}

			return returnValue;
		}
		
		public String generatePasswordResetToken(String userId) {
			Claims claims = Jwts.claims();
			String token = Jwts.builder().setClaims(claims)
					.setSubject(userId)
					.signWith(Keys.hmacShaKeyFor("J0bApplicati0nApp@cd$ss20_!9fgkC0h0rte5_&&C0h0rt34_S0_B3rl!n_$pr¡ngB00t_$ecur¡ty&&Jwt_Much0D¡n3r0$$$MVC".getBytes()),
							SignatureAlgorithm.HS512).setIssuedAt(new Date())
					.setExpiration(new Date(System.currentTimeMillis() + 3600000L)).compact();
			return token;
	}
}