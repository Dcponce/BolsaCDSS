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

	public List<Alumno> filter(Integer depto, Integer certi, Integer[] habilidades, Integer centro) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String
		if (habilidades != null) {

			String habilidad = "";

			for (Integer i = 0; i < habilidades.length; i++) {
				Habilidad habi = rHabi.findById(habilidades[i]).get();

				if (habi != null) {

					if (habilidades.length == i) {
						habilidad += habi.getId();
					} else {
						habilidad += habi.getId() + ",";
					}
				}
			}

			return iAlumnos.findAllNative(certi, depto, habilidad, centro);

		} else {
			return iAlumnos.findAllNative(certi, depto, null, centro);
		}

	}

	public List<Alumno> filter2(Integer depto, Integer certi, Integer[] habilidades, Integer centro) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative2(certi, depto, habilidad, centro);

	}

	public List<Alumno> filter3(Integer certi, Integer depto, Integer centro) {

		return iAlumnos.findAllNative3(certi, depto, centro);
	}

	public List<Alumno> filter4(Integer certi, Integer depto, Integer[] habilidades) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative4(certi, depto, habilidad);
	}

	public List<Alumno> filter5(Integer depto, Integer[] habilidades, Integer centro) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative5(depto, habilidad, centro);
	}

	public List<Alumno> filter6(Integer[] habilidades, Integer centro, Integer certi) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative6(habilidad, centro, certi);
	}

	public List<Alumno> filter7(Integer depto, Integer certi) {

		return iAlumnos.findAllNative7(certi, depto);
	}

	public List<Alumno> filter8(Integer certi, Integer centro) {

		return iAlumnos.findAllNative8(certi, centro);
	}

	public List<Alumno> filter9(Integer centro, Integer depto) {

		return iAlumnos.findAllNative9(depto, centro);
	}

	public List<Alumno> filter10(Integer[] habilidades, Integer certi) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative10(habilidad, certi);
	}
	
	public List<Alumno> filter11(Integer[] habilidades, Integer depto) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative11(habilidad, depto);
	}
	
	public List<Alumno> filter12(Integer[] habilidades, Integer centro) {

		// Se recibe un arreglo de tipo Integer el cual se convierte a String

		String habilidad = "";

		for (Integer i = 0; i < habilidades.length; i++) {
			Habilidad habi = rHabi.findById(habilidades[i]).get();

			if (habi != null) {

				if (habilidades.length == i) {
					habilidad += habi.getId();
				} else {
					habilidad += habi.getId() + ",";
				}
			}
		}

		return iAlumnos.findAllNative12(habilidad, centro);
	}

}
