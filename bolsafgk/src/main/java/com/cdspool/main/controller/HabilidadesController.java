package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Habilidad;
import com.cdspool.main.repository.IHabilidadRepository;

@RestController
@RequestMapping(value = "habilidades")
public class HabilidadesController {

	@Autowired
	IHabilidadRepository rDetalle;
	
	@GetMapping
	public List<Habilidad> listar(){
		return (List<Habilidad>) rDetalle.findAll();
	}
	
	@PostMapping
	public void guardar(@RequestBody Habilidad habilidad) {
		rDetalle.save(habilidad);
	}
	
	@PutMapping
	public void edit(@RequestBody Habilidad habilidad) {
		rDetalle.save(habilidad);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		rDetalle.deleteById(id);
	}
}
