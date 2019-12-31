package com.cdspool.main.auth;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/* El metodo de esta clase unifica con SimpleGrantedAuthority al momento de crear la 
 * Collection<? extends GrantedAuthority> en el metodo getRoles de la clase JWTServiceImp
*/
public abstract class SimpleGrantedAuthorityMixin {

	@JsonCreator
	public SimpleGrantedAuthorityMixin(@JsonProperty("authority") String role) {

	}

}
