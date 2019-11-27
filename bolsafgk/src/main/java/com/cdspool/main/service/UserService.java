package com.cdspool.main.service;

import javax.mail.MessagingException;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.cdspool.main.shared.UserDto;

public interface UserService extends UserDetailsService{

	UserDto createUser(UserDto user);
	UserDto getUser(String email);
	UserDto getUserByCredencial(String credencial);
	boolean verifyEmailToken(String token);
	boolean requestPasswordReset(String email) throws MessagingException;
}
