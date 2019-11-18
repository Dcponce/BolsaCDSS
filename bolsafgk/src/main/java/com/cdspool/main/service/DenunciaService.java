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
	
	//Metodo Listar
	public List<Denuncia>listar(){
		return (List<Denuncia>)rDenuncia.findAll();
	}
	
	//Metodo Eliminar
	public void eliminar(Integer id) {
		rDenuncia.deleteById(id);
	}
	
	//Metodo guardar
	public void guardar(Denuncia denuncia) {
		rDenuncia.save(denuncia);
	}
	
	//Medoto Buscar por id
	public Denuncia PorDenuncia(Integer id) {
		return rDenuncia.findById(id).get();
	}
	
	//Metodo listar
	public List<Usuario>lista(){
		return (List<Usuario>)rUsuario.findAll();
		
	}
	
	//Metodo Buscar por Id
	public Usuario porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
}
