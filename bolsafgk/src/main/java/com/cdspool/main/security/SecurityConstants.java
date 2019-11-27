package com.cdspool.main.security;

import com.cdspool.main.SpringApplicationContext;

public class SecurityConstants {

	public static final long EXPIRATION_TIME = 864000000;
	public static final long PASSWORD_RESET_EXPIRATION_TIME = 360000; // 1 HORA
	public static final String TOKEN_PREFIX = "Bearer";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/usuarios"; 
	public static final String VERIFICATION_EMAIL_URL = "/usuarios/email-verification";
	public static final String PASSWORD_RESET_REQUEST_URL = "/password-reset-request";
	
	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("AppProperties");
		return appProperties.getTokenSecret();
	}
}