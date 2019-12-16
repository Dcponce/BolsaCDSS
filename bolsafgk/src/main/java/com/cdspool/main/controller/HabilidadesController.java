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
	IHabilidadRepository rHabilidad;

	@GetMapping
	public List<Habilidad> listar() {
		return (List<Habilidad>) rHabilidad.findAll();
	}

	@PostMapping
	public void guardar(@RequestBody Habilidad habi) {
		rHabilidad.save(habi);
	}

	@PutMapping
	public void actualizar(@RequestBody Habilidad habi) {
		rHabilidad.save(habi);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		rHabilidad.deleteById(id);
	}

	@GetMapping("/habi/{id}")
	public Habilidad porHabilidad(@PathVariable Integer id) {
		return rHabilidad.findById(id).get();
	}

}
