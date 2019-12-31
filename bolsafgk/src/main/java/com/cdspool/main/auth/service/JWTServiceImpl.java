package com.cdspool.main.auth.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.util.Base64Utils;

import com.cdspool.main.auth.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JWTServiceImpl implements JWTService {

	// Clave para generar Token
	public static final String SECRET = Base64Utils.encodeToString(
			"J0bApplicati0nApp@cd$ss20_!9fgkC0h0rte5_&&C0h0rt34_S0_B3rl!n_$pr¡ngB00t_$ecur¡ty&&Jwt_Much0D¡n3r0$$$MVC"
					.getBytes());
	
	// Tiempo del expiracion(24hr)
	public static final long EXPIRATION_DATE = 86400000L;
	
	// Prefijo del token 
	public static final String TOKEN_PREFIX = "Bearer ";

	// Encabezado del token
	public static final String HEADER_STRING = "Authorization";
	
	// Crear token
	public String create(Authentication auth) throws JsonProcessingException {

		String email = ((User) auth.getPrincipal()).getUsername();

		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

		Claims claims = Jwts.claims();

		claims.put("tipo", new ObjectMapper().writeValueAsString(roles));

		String token = Jwts.builder().setClaims(claims).setSubject(email).signWith(Keys.hmacShaKeyFor(

				SECRET.getBytes()), SignatureAlgorithm.HS512).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_DATE)).compact();

		return token;
	}

	// Validar token
	public boolean validate(String token) {

		try {

			getClaims(token);

			return true;

		} catch (JwtException | IllegalArgumentException e) {

			return false;

		}

	}

	// Obtenemos las propiedades
	public Claims getClaims(String token) {

		Claims claims = Jwts.parser().setSigningKey(SECRET.getBytes()).parseClaimsJws(resolve(token)).getBody();

		return claims;

	}

	public String getUsername(String token) {

		return getClaims(token).getSubject();
	}

	// Obtenemos los roles mediante el metodo anterior(getClaims)
	public Collection<? extends GrantedAuthority> getRoles(String token) throws IOException {

		Object roles = getClaims(token).get("tipo");

		Collection<? extends GrantedAuthority> authorities = Arrays
				.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
						.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));

		return authorities;
	}

	// Verificamos el token 
	public String resolve(String token) {

		if (token != null && token.startsWith(TOKEN_PREFIX)) {
			return token.replace(TOKEN_PREFIX, "");
		}

		return null;
	}

}
