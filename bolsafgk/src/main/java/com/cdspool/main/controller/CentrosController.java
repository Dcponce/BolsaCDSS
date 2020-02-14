package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Centros;
import com.cdspool.main.repository.ICentrosRepository;

@RestController
@RequestMapping(value = "centros")
public class CentrosController {

	@Autowired
	ICentrosRepository rCentros;
	
	//Listar centros cds
	@GetMapping
	@Secured({ "ROLE_ALUMNO", "ROLE_ADMIN", "ROLE_EMPRESA" })
	public List<Centros> lista(){
		return (List<Centros>) rCentros.findAll();
	}
	
	//Eliminar centros
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete (@PathVariable Integer id) {
		rCentros.deleteById(id);
	}
	
	//Ingresar centros
	@PostMapping
	@Secured("ROLE_ADMIN")
	public void save(@RequestBody Centros centros) {
		rCentros.save(centros);
	}
	
	//Editar centros
	@PutMapping
	@Secured("ROLE_ADMIN")
	public void update(@RequestBody Centros centros) {
		rCentros.save(centros);
	}
	
	// Listar por id
	@GetMapping("/{id}")
	public Centros byId(@PathVariable Integer id) {
		return rCentros.findById(id).get();
	}
}
