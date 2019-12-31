package com.cdspool.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cdspool.main.model.ClaveTemporal;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IClaveTeRepository;
import com.cdspool.main.repository.IUsuarioRepository;


@Service
@Transactional
public class ClaveTeService {
	
	@Autowired
	IClaveTeRepository rClave;
	
	@Autowired 
	IUsuarioRepository rUsuario;
	
	public void eliminar(Integer id) {
		rClave.deleteById(id);
	}
	
	public void guardar(ClaveTemporal claveT) {
		rClave.save(claveT);
	}
	
	public ClaveTemporal porClavet(Integer id) {
		return rClave.findById(id).get();
	}
	
	public Usuario porUsuario(Integer id) {
		return rUsuario.findById(id).get();
	}
	
	public ClaveTemporal findByClavet(String clavet) {
		return rClave.findByClavet(clavet);
	}
}


