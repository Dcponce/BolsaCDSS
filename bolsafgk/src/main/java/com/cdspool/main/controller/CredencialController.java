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

import com.cdspool.main.model.Credencial;
import com.cdspool.main.repository.ICredencialRepository;

@RestController
@RequestMapping(value = "credencial")
@CrossOrigin(origins = "*", methods =  {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CredencialController {

	@Autowired
	ICredencialRepository iCred;

	@GetMapping("/lista")
	// @Secured("ROLE_ALUMNO")
	public List<Credencial> lista() {
		return (List<Credencial>) iCred.findAll();
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		iCred.deleteById(id);
	}

	@PostMapping
	public void add(@RequestBody Credencial cred) {
		iCred.save(cred);
	}

	@PutMapping
	public void update(@RequestBody Credencial cred) {
		iCred.save(cred);
	}

	@GetMapping("/credi/{id}")
	public Credencial porCredencial(@PathVariable Integer id) {
		return iCred.findById(id).get();
	}

}
