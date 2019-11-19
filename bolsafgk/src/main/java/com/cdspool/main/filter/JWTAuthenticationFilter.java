package com.cdspool.main.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

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

}
