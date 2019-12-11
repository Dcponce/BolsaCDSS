package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Departamentos;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.repository.IDepartamentosRepository;
import com.cdspool.main.service.MunicipiosService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "municipios")
public class MunicipiosController {

	@Autowired //Manda a llamar el servicio de municipio
	MunicipiosService sMunicipios;
	
	@Autowired //Manda a llamar el repositorio de departamento
	IDepartamentosRepository idepa;
	
	//Ejecución del metodo Listar
	@GetMapping
	public List<Municipios> listar(){
		return (List<Municipios>) sMunicipios.listar();
	}
	
	///Ejecución del metodo buscar el id de Municipio
	@GetMapping("/municipio/{id}")
	public Municipios getMunicipio(@PathVariable Integer id) {
		return sMunicipios.porMunicipio(id);
	}
	
	///Ejecución del metodo listar de departamento
	@GetMapping("api/muny")
	public List<Departamentos> listDepa(){
		
		List<Departamentos> list = (List<Departamentos>) idepa.findAll();
		
		return list;
	}
	
	///Ejecución del metodo buscar el id de Municipio
	@GetMapping("/muni/depar/{id}")
	public Departamentos getDepartamento(@PathVariable Integer id) {
		return sMunicipios.porDepartamentos(id);
	}
	
	
}
