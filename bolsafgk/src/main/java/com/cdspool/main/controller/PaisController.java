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

import com.cdspool.main.model.Pais;
import com.cdspool.main.repository.IPaisRepository;

@RestController
@RequestMapping(value = "pais")
public class PaisController {
	
	@Autowired
	IPaisRepository rPais;
	
	@GetMapping
	public List<Pais> findAll(){
		return (List<Pais>) rPais.findAll();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		rPais.deleteById(id);
	}
	
	@PostMapping()
	public void add(@RequestBody Pais pais) {
		rPais.save(pais);
	}
	
	@PutMapping()
	public void update(@RequestBody Pais pais) {
		rPais.save(pais);
	}
	
	@GetMapping("/{id}")
	public Pais byId(@PathVariable Integer id) {
		return rPais.findById(id).get();
	}

}
