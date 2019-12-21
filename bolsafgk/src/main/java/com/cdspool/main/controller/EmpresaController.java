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

import com.cdspool.main.model.Empresa;
import com.cdspool.main.service.EmpresaService;

@RestController
@RequestMapping(value = "empresa")
public class EmpresaController {

	@Autowired // Manda a llamar servicio de empresa
	EmpresaService empresaService;

	// Ejecución del metodo listar

	@GetMapping
	public List<Empresa> listar() {
		return empresaService.listar();
	}

	// Ejecución del metodo agregar
	@PostMapping
	public void addEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}

	// Ejecución del metodo editar
	@PutMapping
	public void editEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}

	// Ejecución del metodo eliminar
	@DeleteMapping("/{id}")
	public void deleteEm(@PathVariable Integer id) {
		empresaService.deleteEmp(id);
	}

	/// Ejecución del metodo buscar el id de empresa
	@GetMapping("/empre/{id}")
	public Empresa getEmpresa(@PathVariable Integer id) {
		return empresaService.findByUsuario(id);
	}

}