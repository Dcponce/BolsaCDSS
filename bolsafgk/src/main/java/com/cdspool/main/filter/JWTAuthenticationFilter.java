package com.cdspool.main.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.cdspool.main.auth.service.JWTService;
import com.cdspool.main.auth.service.JWTServiceImpl;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IUsuarioRepository;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@CrossOrigin(origins = "*")
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	@Autowired
	IUsuarioRepository rUsu;

	private AuthenticationManager authenticationManager;
	private JWTService jwtService;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jwtService) {

		this.authenticationManager = authenticationManager;
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));

		this.jwtService = jwtService;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		String email = request.getParameter("email");
		String clave = request.getParameter("clave");

		if (email != null && clave != null) {
			logger.info("Email desde request parameter (form-data): " + email);
			logger.info("Clave desde request parameter (form-data): " + clave);

		} else {

			Usuario user;

			try {

				user = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);

				email = user.getEmail();
				clave = user.getClave();

				logger.info("Email desde request InputStream (raw): " + email);
				logger.info("Clave desde request InputStream (raw): " + clave);

			} catch (JsonParseException e) {

				e.printStackTrace();

			} catch (JsonMappingException e) {

				e.printStackTrace();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		email = email.trim();

		UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(email, clave);

		return authenticationManager.authenticate(authToken);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String token = jwtService.create(authResult);

		response.addHeader(JWTServiceImpl.HEADER_STRING, JWTServiceImpl.TOKEN_PREFIX + token);

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("token", "Bearer " + token);
		body.put("user", (User) authResult.getPrincipal());
		body.put("mensaje",
				String.format("%s ¡Has iniciado Sesion con exito!", ((User) authResult.getPrincipal()).getUsername()));

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(200);
		response.setContentType("application/json");

	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {

		Map<String, Object> body = new HashMap<String, Object>();

		body.put("mensaje", "Error de autenticacion: email o password incorrecto!");
		body.put("error", failed.getMessage());

		response.getWriter().write(new ObjectMapper().writeValueAsString(body));
		response.setStatus(401);
		response.setContentType("application/json");
	}

}
