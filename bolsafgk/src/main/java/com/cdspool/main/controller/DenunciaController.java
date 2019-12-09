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

import com.cdspool.main.model.Denuncia;
import com.cdspool.main.service.DenunciaService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "denuncia")
public class DenunciaController {

	@Autowired
	DenunciaService sDenuncia;
	
	//Metodo listar
	@GetMapping
	public List<Denuncia>listar(){
		return sDenuncia.listar();
	}
	
	//Eliminar
	@DeleteMapping("/{id}")
	public void eliminar(@PathVariable Integer id) {
		sDenuncia.eliminar(id);
	}
	
	//Agregar
	@PostMapping
	public void guardar(@RequestBody Denuncia denuncia) {
		sDenuncia.guardar(denuncia);
	}
	
	//Buscar
	@GetMapping("/denuncias/{id}")
	public Denuncia buscar(@PathVariable Integer id) {
		return sDenuncia.PorDenuncia(id);
	}
	
	
}
