package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Credencial;
import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.ICredencialRepository;
import com.cdspool.main.repository.ITipoUsuarioRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	IUsuarioRepository iUsuario;

	@Autowired
	ITipoUsuarioRepository iTipo;

	@Autowired
	ICredencialRepository iCred;

	public List<Usuario> findAll() {
		return (List<Usuario>) iUsuario.findAll();
	}

	public Usuario findById(Integer id) {
		return iUsuario.findById(id).get();
	}

	public void delete(Integer id) {
		iUsuario.deleteById(id);
	}

	public void save(Usuario usu) {
		iUsuario.save(usu);
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

}
