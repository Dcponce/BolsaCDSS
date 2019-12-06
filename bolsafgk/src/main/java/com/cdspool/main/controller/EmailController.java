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

import com.cdspool.main.model.Email;
import com.cdspool.main.service.EmailService;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE} )
@RequestMapping(value = "email")
public class EmailController {

	@Autowired
	EmailService emailService;
	
	@GetMapping
	public List<Email> listarEmail(){
		return emailService.listAllEmail();
	}
	
	@PostMapping
	public void add(@RequestBody Email email) {
		emailService.save(email);
	}
	
	@PutMapping
	public void edit(@RequestBody Email email) {
		emailService.save(email);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		emailService.deleteEmail(id);
	}
	
	@GetMapping("/correos/{id}")
	public Email leer(@PathVariable Integer id) {
		return emailService.leer(id);
	}
}