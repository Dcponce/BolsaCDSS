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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Credencial;
import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.ICredencialRepository;
import com.cdspool.main.repository.ITipoUsuarioRepository;
import com.cdspool.main.repository.IUsuarioRepository;
import com.cdspool.main.repository.UserRepository;

@Service
@Transactional
public class UsuarioService implements UserDetailsService {

	@Autowired
	IUsuarioRepository iUsu;

	@Autowired
	ITipoUsuarioRepository iTipo;

	@Autowired
	ICredencialRepository iCred;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	ICredencialRepository iCreden;
	
	

	public List<Usuario> findAll() {
		return (List<Usuario>) iUsu.findAll();
	}

	public Usuario findById(Integer id) {
		return iUsu.findById(id).get();
	}

	public void delete(Integer id) {
		iUsu.deleteById(id);
	}

	public void save(Usuario usu) {
		iUsu.save(usu);
	}

	public List<TipoUsuario> findAllTipo() {
		return (List<TipoUsuario>) iTipo.findAll();
	}

	public TipoUsuario findByIdTipo(Integer id) {
		return iTipo.findById(id).get();
	}

	public List<Credencial> findAllCred() {
		return (List<Credencial>) iCred.findAll();
	}

	public Credencial findByIdCred(Integer id) {
		return iCred.findById(id).get();
	}
	
	public Credencial findByCodigo(String codigo) {
		return iCreden.findByCodigo(codigo);
	}

	private Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Usuario usua = userRepository.findByEmail(email);

		if (usua == null) {
			logger.error("Error login: Usuario no existe '" + email + "'");
			throw new UsernameNotFoundException("Usuario " + email + "no existe");
		}

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		TipoUsuario tipo = usua.getId_tipo();

		logger.info("Tipo: ".concat(tipo.getDescripcion()));

		authorities.add(new SimpleGrantedAuthority(tipo.getDescripcion()));

		if (authorities.isEmpty()) {
			logger.error("Error login: Usuario '" + email + "' no tiene asignado el rol");
			throw new UsernameNotFoundException("Error login: Usuario '" + email + "' no tiene asignado el rol");
		}
		
		return new User(usua.getEmail(), usua.getClave(), usua.getEstado(), true, true, true, authorities);
	}
}