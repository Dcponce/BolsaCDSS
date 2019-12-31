package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Denuncia;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IDenunciaRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class DenunciaService {

	@Autowired
	IDenunciaRepository rDenuncia;

	@Autowired
	IUsuarioRepository rUsuario;
	
	public List<Denuncia>listar(){
		return (List<Denuncia>)rDenuncia.findAll();
	}
	
	public void eliminar(Integer id) {
		rDenuncia.deleteById(id);
	}
	
	public void guardar(Denuncia denuncia) {
		rDenuncia.save(denuncia);
	}
	
	public Denuncia PorDenuncia(Integer id) {
		return rDenuncia.findById(id).get();
	}
	
	public List<Usuario>lista(){
		return (List<Usuario>)rUsuario.findAll();
		
	}
	
	public Usuario porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
}
