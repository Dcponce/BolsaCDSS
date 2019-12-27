package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.Documento;
import com.cdspool.main.model.TipoDocumento;
import com.cdspool.main.service.DocumentoService;

@RestController
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })
@RequestMapping(value = "documento")
public class DocumentoController {

	@Autowired
	DocumentoService dService;

	@DeleteMapping("/{id}")
	@Secured("ROLE_ADMIN")
	public void delete(@PathVariable Integer id) {
		dService.delete(id);
	}

	@PostMapping
	@Secured({ "ROLE_ALUMNO", "ROLE_EMPRESA", "ROLE_ADMIN" })
	public void add(@RequestBody Documento doc) {
		dService.save(doc);
	}

	@PutMapping
	@Secured({ "ROLE_ALUMNO", "ROLE_EMPRESA", "ROLE_ADMIN" })
	public void update(@RequestBody Documento doc) {
		dService.save(doc);
	}

	@GetMapping("/{id}")
	@Secured({ "ROLE_ALUMNO", "ROLE_EMPRESA", "ROLE_ADMIN" })
	public TipoDocumento findById(@PathVariable Integer id) {
		return dService.findByIdTipo(id);
	}

	@GetMapping("usuario/{id}")
	@Secured({ "ROLE_ALUMNO", "ROLE_EMPRESA", "ROLE_ADMIN" })
	public Documento idUsuario(@PathVariable Integer id) {
		return dService.findByUsuario(id);
	}

}
