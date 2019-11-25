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
@Table(name = "detalle_habilidades")
public class Detalle_habilidades {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JoinColumn(name = "id_habilidad")
	@ManyToOne(fetch = FetchType.EAGER)
	private Habilidad habilidad;

	@JoinColumn(name = "id_usuario")
	@ManyToOne(fetch = FetchType.EAGER)
	private Alumno usuarios;

	private Integer prioridad;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Habilidad getHabilidad() {
		return habilidad;
	}

	public void setHabilidad(Habilidad habilidad) {
		this.habilidad = habilidad;
	}

	public Alumno getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Alumno usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getPrioridad() {
		return prioridad;
	}

	public void setPrioridad(Integer prioridad) {
		this.prioridad = prioridad;
	}

}
