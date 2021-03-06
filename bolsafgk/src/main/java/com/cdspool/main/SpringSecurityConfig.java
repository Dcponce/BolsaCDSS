package com.cdspool.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.cdspool.main.auth.service.JWTService;
import com.cdspool.main.filter.JWTAuthenticationFilter;
import com.cdspool.main.filter.JWTAuthorizationFilter;
import com.cdspool.main.service.LoginService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoginService login;

	@Autowired
	private JWTService jwtService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// Configuracion Global de Cors
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.addAllowedOrigin("*");
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		return source;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.cors().and().authorizeRequests().antMatchers(HttpMethod.POST, "/login/**").permitAll()
				.antMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
				.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
				.antMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()
				.antMatchers(HttpMethod.POST, "/envio/temporal").permitAll()
				.antMatchers(HttpMethod.GET, "/temporal/**").permitAll()
				.antMatchers(HttpMethod.DELETE, "/temporal/**").permitAll().anyRequest().authenticated().and().csrf()
				.disable().authorizeRequests().and()
				// filtros JWT
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService)).sessionManagement()
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(login).passwordEncoder(passwordEncoder);
	}

}
