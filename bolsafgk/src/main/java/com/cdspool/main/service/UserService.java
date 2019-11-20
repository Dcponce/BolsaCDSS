package com.cdspool.main.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{

	boolean requestPasswordReset(String email);
}
