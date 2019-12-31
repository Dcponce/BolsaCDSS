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
	
	public List<Proyecto> listar(){
		return (List<Proyecto>)rProyecto.findAll();
	}
	
	public void eliminar(Integer id) {
		rProyecto.deleteById(id);
	}
	
	public void agregar(Proyecto proyecto) {
		rProyecto.save(proyecto);
	}
	
	public Proyecto porProyecto(Integer id) {
		return rProyecto.findById(id).get();
	}
	
	public List<Proyecto> findByUsuario(Integer id) {
		Usuario user = rUsuario.findById(id).get();
		return rProyecto.findByUsuario(user);
	}
}
