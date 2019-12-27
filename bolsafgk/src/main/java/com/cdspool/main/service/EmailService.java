package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Email;
import com.cdspool.main.repository.IEmailRepository;

@Service
@Transactional
public class EmailService {

	@Autowired
	IEmailRepository rEmail;

	public List<Email> listAllEmail() {
		return (List<Email>) rEmail.findByOrderByIdDesc();
	}

	public void deleteEmail(Integer id) {
		rEmail.deleteById(id);
	}

	public void save(Email email) {
		rEmail.save(email);
	}

	public Email leer(Integer id) {
		return rEmail.findById(id).get();
	}

}