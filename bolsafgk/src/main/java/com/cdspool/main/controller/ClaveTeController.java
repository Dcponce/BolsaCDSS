package com.cdspool.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.service.ClaveTeService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "temporal")
public class ClaveTeController {

	@Autowired
	ClaveTeService sClave;

	// Agregar
	@PostMapping
	public void agregar(@RequestBody ClaveTemporal clavet) {
		sClave.guardar(clavet);
	}

	// Actualizar
	@PutMapping
	public void actualizar(@RequestBody ClaveTemporal clavet) {
		sClave.guardar(clavet);
	}

	// Eliminar
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		sClave.eliminar(id);
	}
}
