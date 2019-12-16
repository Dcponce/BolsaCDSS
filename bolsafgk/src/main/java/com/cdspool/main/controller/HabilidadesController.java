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

import com.cdspool.main.model.Detalle_habilidades;
import com.cdspool.main.model.Habilidad;
import com.cdspool.main.service.Detalle_HabilidadService;

@RestController
@RequestMapping(value = "habilidades")
public class HabilidadesController {

	@Autowired
	Detalle_HabilidadService rDetalle;

	@GetMapping
	public List<Habilidad> listar() {
		return rDetalle.listarHa();
	}

	@PostMapping
	public void guardar(@RequestBody Detalle_habilidades detalle) {
		rDetalle.saveDe(detalle);
	}

	@PutMapping
	public void edit(@RequestBody Detalle_habilidades habilidad) {
		rDetalle.saveDe(habilidad);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		rDetalle.eliminarDe(id);
	}

}
