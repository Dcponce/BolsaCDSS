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

import com.cdspool.main.model.Certificacion;
import com.cdspool.main.repository.ICertificacionRepository;

@RestController
@RequestMapping(value = "certificacion")
public class CertificacionController {

	@Autowired
	ICertificacionRepository icerty;

	// Listar Certificaciones
	@GetMapping
	@Secured({"ROLE_ADMIN", "ROLE_EMPRESA", "ROLE_ALUMNO"})
	public List<Certificacion> lista() {
		return (List<Certificacion>) icerty.findAll();
	}

	// Eliminar Certificacion
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Integer id) {
		icerty.deleteById(id);
	}

	// Agregar Certificacion 
	@PostMapping
	@Secured("ROLE_ADMIN")
	public void add(@RequestBody Certificacion certy) {
		icerty.save(certy);
	}

	// Editar Certificacion 
	@PutMapping
	@Secured("ROLE_ADMIN")
	public void update(@RequestBody Certificacion certy) {
		icerty.save(certy);
	}

	// Listar Certificacion por id
	@GetMapping("/byId/{id}")
	@Secured("ROLE_ADMIN")
	public Certificacion findById(@PathVariable Integer id) {
		return icerty.findById(id).get();
	}

}
