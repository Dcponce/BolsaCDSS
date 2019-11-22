package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Proyecto;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IProyectoRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class ProyectoService {

	@Autowired
	IProyectoRepository rProyecto;
	
	@Autowired
	IUsuarioRepository rUsuario;
	
	//Método listar
	public List<Proyecto> listar(){
		return (List<Proyecto>)rProyecto.findAll();
	}
	
	//Método Eliminar
	public void eliminar(Integer id) {
		rProyecto.deleteById(id);
	}
	
	//Método Agregar
	public void agregar(Proyecto proyecto) {
		rProyecto.save(proyecto);
	}
	
	//Método Busca por id
	public Proyecto porProyecto(Integer id) {
		return rProyecto.findById(id).get();
	}
	
	//Método listar usuario
	public List<Usuario>lista(){
		return (List<Usuario>)rUsuario.findAll();
	}
	
	//Método Buscar por Id
	public Usuario porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
}
