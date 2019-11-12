package com.cdspool.main.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "educacion")
public class Educacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String universidad;

	private String carrera;

	@JoinColumn(name = "id_certificacion")
	private Certificacion id_certificacion;

	@JoinColumn(name = "id_usuario")
	private Usuarios id_usuario;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUniversidad() {
		return universidad;
	}

	public void setUniversidad(String universidad) {
		this.universidad = universidad;
	}

	public String getCarrera() {
		return carrera;
	}

	public void setCarrera(String carrera) {
		this.carrera = carrera;
	}

	public Certificacion getId_certificacion() {
		return id_certificacion;
	}

	public void setId_certificacion(Certificacion id_certificacion) {
		this.id_certificacion = id_certificacion;
	}

	public Usuarios getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuarios id_usuario) {
		this.id_usuario = id_usuario;
	}

}
