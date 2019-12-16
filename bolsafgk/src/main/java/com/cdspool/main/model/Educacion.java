package com.cdspool.main.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Certificacion id_certificacion;

	private String nivel;

	@JoinColumn(name = "id_usuario")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;

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

	public String getNivel() {
		return nivel;
	}

	public void setNivel(String nivel) {
		this.nivel = nivel;
	}

	public Certificacion getId_certificacion() {
		return id_certificacion;
	}

	public void setId_certificacion(Certificacion id_certificacion) {
		this.id_certificacion = id_certificacion;
	}

	public Usuario getId_usuario() {
		return usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.usuario = id_usuario;
	}

}
