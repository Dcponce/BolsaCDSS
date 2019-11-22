package com.cdspool.main.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {

		this.authenticationManager = authenticationManager;

		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String email = request.getParameter("email"); // obtainUsername(request);
		String clave = request.getParameter("clave"); // obtainPassword(request);

		if (email == null) {
			email = "";
		}

		if (clave == null) {
			clave = "";
		}

		if (email != null && clave != null) {
			logger.info("Email desde request parameter: " + email);
			logger.info("Clave desde request parameter: " + clave);
		}

		email = email.trim();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, clave);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String email = ((User) authResult.getPrincipal()).getUsername();

		String token = Jwts.builder().setSubject(email)
				.signWith(Keys.hmacShaKeyFor(
						"J0bApplicati0nApp@cd$ss20_!9fgkC0h0rte5_&&C0h0rt34_S0_B3rl!n_$pr¡ngB00t_$ecur¡ty&&Jwt_Much0D¡n3r0$$$MVC"
								.getBytes()),
						SignatureAlgorithm.HS512)
				.compact();

		response.addHeader("Authorization", "Bearer " + token);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("token", token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje", String.format("Hola %s ¡Has iniciado Sesion con exito!", email));

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");

	}

}
