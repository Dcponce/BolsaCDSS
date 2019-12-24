package com.cdspool.main.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.model.Habilidad;
import com.cdspool.main.model.Usuario;
import com.cdspool.main.repository.IAlumnoRepository;
import com.cdspool.main.repository.ICertificacionRepository;
import com.cdspool.main.repository.IDepartamentosRepository;
import com.cdspool.main.repository.IHabilidadRepository;
import com.cdspool.main.repository.IUsuarioRepository;

@Service
@Transactional
public class AlumnoService {

	@Autowired
	IAlumnoRepository iAlumnos;
	
	@Autowired
	IHabilidadRepository rHabi;

	@Autowired
	IDepartamentosRepository rDepto;

	@Autowired
	ICertificacionRepository rCerti;

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

	public Alumno findByUsuario(Integer id) {
		Usuario user = iUsuario.findById(id).get();
		return iAlumnos.findByUsuario(user);
	}

//	public List<Alumno> filter(Integer depto, Integer certi, Integer[] habilidades) {
//
//		Certificacion cer = rCerti.findById(certi).get();
//		Departamentos depa = rDepto.findById(depto).get();
//
//		String habilidad = "";
//		for (Integer i = 0; i < habilidades.length; i++) {
//			Habilidad habi = rHabi.findById(habilidades[i]).get();
//
//			if (habi != null) {
//
//				if (habilidades.length == i) {
//					habilidad += habi.getId();
//				} else {
//					habilidad += habi.getId() + ",";
//				}
//			}
//		}
//		
//		return iAlumnos.findAlumnoByCertificacionOrDepartamentoOrHabilidadNamedParamsNative(certi, depto, habilidad);
//
//	}
}
