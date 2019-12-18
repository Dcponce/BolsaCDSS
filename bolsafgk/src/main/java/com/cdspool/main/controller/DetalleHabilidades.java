package com.cdspool.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.cdspool.main.model.Detalle_habilidades;
import com.cdspool.main.service.Detalle_HabilidadService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "detalleHa")
public class DetalleHabilidades {

	@Autowired
	Detalle_HabilidadService deService;
	
	@GetMapping
	public List<Detalle_habilidades> listar(){
		return (List<Detalle_habilidades>) deService.listar();
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		deService.eliminarDe(id);
	}
	
	@PostMapping
	public void guardar(@RequestBody Detalle_habilidades detalle) {
		deService.saveDe(detalle);
	}
	
	@PutMapping
	public void edit(@RequestBody Detalle_habilidades detalle) {
		deService.saveDe(detalle);
	}
	
	@GetMapping("usuario/{id}")
	public List<Detalle_habilidades>idUsuario(@PathVariable Integer id){
		return deService.findByUsuario(id);
	}
}
