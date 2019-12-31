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

import com.cdspool.main.model.Empresa;
import com.cdspool.main.service.EmpresaService;

@RestController
@RequestMapping(value = "empresa")
public class EmpresaController {

	@Autowired 
	EmpresaService empresaService;

	// Listar Empresas
	@GetMapping
	@Secured("ROLE_ADMIN")
	public List<Empresa> listar() {
		return empresaService.listar();
	}

	// Agregar Empresa
	@PostMapping
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public void addEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}

	// Editar Empresa
	@PutMapping
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public void editEm(@RequestBody Empresa empresa) {
		empresaService.saveEmp(empresa);
	}

	// Eliminar Empresa
	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void deleteEm(@PathVariable Integer id) {
		empresaService.deleteEmp(id);
	}

	// Listar por id Usuario
	@GetMapping("/empre/{id}")
	@Secured({ "ROLE_ADMIN", "ROLE_EMPRESA" })
	public Empresa getEmpresa(@PathVariable Integer id) {
		return empresaService.findByUsuario(id);
	}

}