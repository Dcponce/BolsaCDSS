package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Denuncia;
import com.cdspool.main.service.DenunciaService;

@RestController
@RequestMapping(value = "denuncia")
public class DenunciaController {

	@Autowired
	DenunciaService sDenuncia;

	// Listar Denuncia
	@GetMapping
	@Secured("ROLE_ADMIN")
	public List<Denuncia> listar() {
		return sDenuncia.listar();
	}

	// Eliminar Denuncia
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void eliminar(@PathVariable Integer id) {
		sDenuncia.eliminar(id);
	}

	// Agregar Denuncia
	@PostMapping
	@Secured({"ROLE_EMPRESA", "ROLE_ALUMNO"})
	public void guardar(@RequestBody Denuncia denuncia) {
		sDenuncia.guardar(denuncia);
	}

	// Listar Denuncia por id
	@GetMapping("/denuncias/{id}")
	@Secured("ROLE_ADMIN")
	public Denuncia buscar(@PathVariable Integer id) {
		return sDenuncia.PorDenuncia(id);
	}

}
