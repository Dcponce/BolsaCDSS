package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.model.Municipios;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IAlumnoRepository;
import com.cdspool.main.repository.IMunicipiosRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class AlumnoService {

	@Autowired
	IAlumnoRepository iAlumnos;

	@Autowired
	IMunicipiosRepository iMunicipios;

	@Autowired
	IUsuarioRepository iUsuario;

	public List<Alumno> findAll() {
		return (List<Alumno>) iAlumnos.findAll();
	}
	
	public Alumno findById(Integer id) {
		return iAlumnos.findById(id).get();
	}

	public void delete(Integer id) {
		iAlumnos.deleteById(id);
	}

	public void save(Alumno usuario) {
		iAlumnos.save(usuario);
	}

	public List<Municipios> findAllMuni() {
		return (List<Municipios>) iMunicipios.findAll();
	}
	
	public Municipios findByIdMuni(Integer id) {
		return iMunicipios.findById(id).get();
	}

	public List<Usuario> findAllUsua() {
		return (List<Usuario>) iUsuario.findAll();
	}
	
	public Usuario findByIdUsua(Integer id) {
		return iUsuario.findById(id).get();
	}

}
