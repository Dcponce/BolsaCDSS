package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Alumno;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.IAlumnoRepository;

@Service
@Transactional
public class ClaveTeService {
	
	@Autowired //Manda a llamar los repositorio
	IClaveTeRepository rClave;
	
	@Autowired //Manda a llamar los repositorio
	IAlumnoRepository rUsuario;
	
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
		rClave.save(claveT);
	}
	
	//Metodo buscar por id
	public ClaveTemporal porClavet(Integer id) {
		return rClave.findById(id).get();
	}
	
	//Metodo Listar
	public List<Alumno> lista(){
		return (List<Alumno>) rUsuario.findAll();
	}
	
	//Metodo Buscar por Id
	public Alumno porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
}


