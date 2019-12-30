package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Departamentos;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.service.MunicipiosService;

@RestController
@Secured({ "ROLE_ADMIN", "ROLE_ALUMNO", "ROLE_EMPRESA" })
@RequestMapping(value = "municipios")
public class MunicipiosController {

	@Autowired
	MunicipiosService sMunicipios;

	// Listar Municipios
	@GetMapping
	public List<Departamentos> listar() {
		return sMunicipios.lista();
	}

	// Listar Municipios por id
	@GetMapping("/municipio/{id}")
	public Municipios getMunicipio(@PathVariable Integer id) {
		return sMunicipios.porMunicipio(id);
	}

	// Listar Municipios por id de Departamento 
	@GetMapping("/muni/{id}")
	public List<Municipios> buscar(@PathVariable Integer id) {
		return sMunicipios.listarM(id);
	}

	// Listar Departamento por id Municipio
	@GetMapping("/muni/depar/{id}")
	public Departamentos getDepartamento(@PathVariable Integer id) {
		return sMunicipios.porDepartamentos(id);
	}

}
