package com.cdspool.main.filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cdspool.main.auth.SimpleGrantedAuthorityMixin;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {

		super(authenticationManager);

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String header = request.getHeader("Authorization");

		if (!requiresAuthentication(header)) {
			chain.doFilter(request, response);
			return;
		}

		boolean tokenValido;

		Claims token = null;

		try {

			token = Jwts.parser().setSigningKey(
					"J0bApplicati0nApp@cd$ss20_!9fgkC0h0rte5_&&C0h0rt34_S0_B3rl!n_$pr¡ngB00t_$ecur¡ty&&Jwt_Much0D¡n3r0$$$MVC"
							.getBytes())
					.parseClaimsJws(header.replace("Bearer ", "")).getBody();

			tokenValido = true;

		} catch (JwtException | IllegalArgumentException e) {

			tokenValido = false;

		}

		UsernamePasswordAuthenticationToken authentication = null;

		if (tokenValido) {
			String email = token.getSubject();
			Object tipo = token.get("tipo");
			Collection<? extends GrantedAuthority> authorities = Arrays.asList(
					new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthorityMixin.class)
							.readValue(tipo.toString().getBytes(), SimpleGrantedAuthority[].class));

			authentication = new UsernamePasswordAuthenticationToken(email, null, authorities);
		}

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(request, response);

	}

	protected boolean requiresAuthentication(String header) {

		if (header == null || !header.startsWith("Bearer ")) {
			return false;
		}

		return true;
	}

}
