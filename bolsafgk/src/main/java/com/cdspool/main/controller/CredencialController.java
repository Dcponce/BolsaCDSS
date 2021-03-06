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

import com.cdspool.main.model.Credencial;
import com.cdspool.main.repository.ICredencialRepository;

@RestController
@Secured("ROLE_ADMIN")
@RequestMapping(value = "credencial")

public class CredencialController {

	@Autowired
	ICredencialRepository iCred;

	// Listar Credenciales
	@GetMapping("/lista")
	public List<Credencial> lista() {
		return (List<Credencial>) iCred.findAll();
	}

	// Eliminar Credencial
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		iCred.deleteById(id);
	}

	// Agregar Credencial
	@PostMapping
	public void add(@RequestBody Credencial cred) {
		iCred.save(cred);
	}

	// Editar Credencial
	@PutMapping
	public void update(@RequestBody Credencial cred) {
		iCred.save(cred);
	}

	// Listar Credencial por id
	@GetMapping("/credi/{id}")
	public Credencial findById(@PathVariable Integer id) {
		return iCred.findById(id).get();
	}

}
