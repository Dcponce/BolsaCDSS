package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Email;
import com.cdspool.main.model.Usuarios;
import com.cdspool.main.repository.IEmailRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class EmailService {

	@Autowired
	IUsuarioRepository rUsuario;
	
	@Autowired
	IEmailRepository rEmail;
	
	public List<Email> listAllEmail(){
		return (List<Email>) rEmail.findAll();
	}
	
	public void deleteEmail(Integer id) {
		rEmail.deleteById(id);
	}
	
	public void save(Email email) {
		rEmail.save(email);
	}
	
	//METODOS ENTIDAD USUARIOS
	
	public List<Usuarios> listAllUsuario(){
		return (List<Usuarios>) rUsuario.findAll();
	}
}