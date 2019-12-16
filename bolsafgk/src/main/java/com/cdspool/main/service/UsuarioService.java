package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioService {

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

	public Usuario getId(String email) {
		return iUsu.findByEmail(email);
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

}