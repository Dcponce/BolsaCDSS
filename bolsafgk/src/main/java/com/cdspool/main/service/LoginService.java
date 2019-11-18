package com.cdspool.main.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IUsuarioRepository;

public class LoginService implements UserDetailsService {

	@Autowired
	IUsuarioRepository iUsu;
	
	private Logger logger =LoggerFactory.getLogger(LoginService.class);

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usua = iUsu.findByEmail(email);
		
		if(usua == null) {
			logger.error("Error login: Usuario no existe '"+ email +"'");
			throw new UsernameNotFoundException("Usuario " + email + "no existe");
		}
		

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		TipoUsuario tipo = usua.getId_tipo();
		
		logger.info("Tipo: ".concat(tipo.getDescripcion()));

		authorities.add(new SimpleGrantedAuthority(tipo.getDescripcion()));
		
		if(authorities.isEmpty()) {
			logger.error("Error login: Usuario '"+ email +"' no tiene asignado el rol");
			throw new UsernameNotFoundException("Error login: Usuario '"+ email +"' no tiene asignado el rol");
		}

		boolean enabled = true;

		return new User(usua.getEmail(), usua.getClave(), enabled, true, true, true, authorities);
	}

}
