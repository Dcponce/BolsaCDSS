package com.cdspool.main.auth.service;

import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import io.jsonwebtoken.Claims;

public class JWTServiceImpl implements JWTService {

	public String create(Authentication auth) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validate(String token) {
		// TODO Auto-generated method stub
		return false;
	}

	public Claims getClaims(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getUsername(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public Collection<? extends GrantedAuthority> getRoles(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	public String resolve(String token) {
		// TODO Auto-generated method stub
		return null;
	}

}
