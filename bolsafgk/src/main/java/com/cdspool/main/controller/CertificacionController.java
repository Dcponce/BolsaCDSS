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

import com.cdspool.main.model.Certificacion;
import com.cdspool.main.repository.ICertificacionRepository;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "certificacion")
public class CertificacionController {
	
	@Autowired
	ICertificacionRepository icerty;
	
	@GetMapping
	public List<Certificacion> lista(){
		
		List<Certificacion> list = (List<Certificacion>) icerty.findAll();
		
		return list;
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		icerty.deleteById(id);
	}
	
	@PostMapping
	public void add(@RequestBody Certificacion certy) {
		icerty.save(certy);
	}
	
	@PutMapping
	public void update(@RequestBody Certificacion certy) {
		icerty.save(certy);
	}

}
