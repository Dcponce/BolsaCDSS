package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.TipoUsuario;
import com.cdspool.main.model.Usuarios;
import com.cdspool.main.repository.ITipoUsuarioRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class UsuarioService {

	@Autowired
	IUsuarioRepository rUsuario;
	
	@Autowired
	ITipoUsuarioRepository rTipoUsuario;
	
	public List<Usuarios> listar(){
		return (List<Usuarios>) rUsuario.findAll();
	}
	
	public void eliminar(Integer id) {
		rUsuario.deleteById(id);
	}
	
	public void guardar(Usuarios usuario) {
		rUsuario.save(usuario);
	}
	
	public List<TipoUsuario> lis(){
		return (List<TipoUsuario>) rTipoUsuario.findAll();
	}
	
}
