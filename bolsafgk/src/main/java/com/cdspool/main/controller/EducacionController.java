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
import com.cdspool.main.model.Educacion;
import com.cdspool.main.service.EducacionService;

@RestController
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
@RequestMapping(value = "educacion")
public class EducacionController {

	@Autowired
	EducacionService eService;

	@GetMapping
	public List<Educacion> lista() {

		List<Educacion> list = eService.findAll();

		return list;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		eService.delete(id);
	}

	@PostMapping
	public void add(@RequestBody Educacion edu) {
		eService.save(edu);
	}

	@PutMapping
	public void update(@RequestBody Educacion edu) {
		eService.save(edu);
	}

	@GetMapping("api/certi")
	public List<Certificacion> listaCerty() {

		List<Certificacion> list = eService.findAllCerti();

		return list;
	}

}
