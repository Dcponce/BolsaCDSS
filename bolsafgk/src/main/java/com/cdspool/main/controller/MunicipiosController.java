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
import com.cdspool.main.service.MunicipiosService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "municipios")
public class MunicipiosController {

	@Autowired
	MunicipiosService sMunicipios;
	
	
	//Listar
	@GetMapping
	public List<Departamentos> listar(){
		return sMunicipios.lista();
	}
	
	//Eliminar
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		sMunicipios.eliminar(id);
		
	}
	
	//Agregar
	@PostMapping
	public void agregar (@RequestBody Municipios municipios) {
		sMunicipios.guardar(municipios);
	}
	
	//Actualizar
	@PutMapping
	public void actualizar(@RequestBody Municipios municipios) {
		sMunicipios.guardar(municipios);
	}
	
	@GetMapping("/muni/{id}")
	public List<Municipios>buscar(@PathVariable Integer id){
		return sMunicipios.listarM(id);
	}
}
