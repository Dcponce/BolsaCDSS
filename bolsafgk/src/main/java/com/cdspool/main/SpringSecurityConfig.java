package com.cdspool.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.cdspool.main.auth.service.JWTService;
import com.cdspool.main.filter.JWTAuthenticationFilter;
import com.cdspool.main.filter.JWTAuthorizationFilter;
import com.cdspool.main.service.UsuarioService;

@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	private UsuarioService uService;

	@Bean
	public UsuarioService uService() {
		return new UsuarioService();
	}

	public SpringSecurityConfig(UsuarioService uService) {
		this.uService = uService;
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private JWTService jwtService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/", "/css/**", "/js/**", "/images/**", "/upload", "/status", "/usuarios/**", "/envio/**")
				.permitAll()/*
							 * .and().formLogin().permitAll().and().logout().permitAll().and().
							 * exceptionHandling() .accessDeniedPage("/error_403")
							 */.and().csrf().disable().authorizeRequests().and()
				.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtService))
				.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtService)).csrf().disable()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Autowired
	public void configurerGlobal(AuthenticationManagerBuilder build) throws Exception {
		build.userDetailsService(uService).passwordEncoder(passwordEncoder());
	}

}
