package com.cdspool.main.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.Certificacion;
import com.cdspool.main.model.Educacion;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.ICertificacionRepository;
import com.cdspool.main.repository.IEducacionRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class EducacionService {
	
	@Autowired
	IEducacionRepository iedu;
	
	@Autowired
	ICertificacionRepository icerti;
	
	@Autowired
	IUsuarioRepository iUsuario;
	
	public List<Educacion> findAll(){
		
		List<Educacion> list = (List<Educacion>) iedu.findAll();
		
		return list;
	}
	
	public Educacion findById(Integer id) {
		return iedu.findById(id).get();
	}
	
	public void delete(Integer id) {
		iedu.deleteById(id);
	}
	
	public void save(Educacion edu) {
		iedu.save(edu);
	}
	
	public List<Certificacion> findAllCerti(){
		
		List<Certificacion> list = (List<Certificacion>) icerti.findAll();
		
		return list;
	}
	
	public Educacion findByUsuario (Integer id) {
		Usuario user = iUsuario.findById(id).get();
		return iedu.findByUsuario(user);
	}
}
