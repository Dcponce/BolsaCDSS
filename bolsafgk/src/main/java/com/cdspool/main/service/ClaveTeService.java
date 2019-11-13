package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Usuarios;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class ClaveTeService {
	
	@Autowired //Manda a llamar los repositorio
	IClaveTeRepository rClave;
	
	@Autowired //Manda a llamar los repositorio
	IUsuarioRepository rUsuario;
	
	//Metodo de listar
	public List<ClaveTemporal>listar(){
		return(List<ClaveTemporal>) rClave.findAll();
	}
	
	//Metodo Eliminar
	public void eliminar(Integer id) {
		rClave.deleteById(id);
	}
	
	//Metodo Guardar
	public void guardar(ClaveTemporal claveT) {
		claveT.setEstado("Activo");
		if(claveT.getId()!=null) {
			claveT.setEstado("Inactivo");
		}
		rClave.save(claveT);
	}
	
	//Metodo buscar por id
	public ClaveTemporal porClavet(Integer id) {
		return rClave.findById(id).get();
	}
	
	//Metodo Listar
	public List<Usuarios> lista(){
		return (List<Usuarios>) rUsuario.findAll();
	}
	
	//Metodo Buscar por Id
	public Usuarios porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
}


