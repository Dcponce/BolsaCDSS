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
@Table(name = "email")
public class Email {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String asunto;
	private String contenido;
	private String estado;
	
	@JoinColumn(name = "usuario_recep")
	@ManyToOne(fetch = FetchType.EAGER)
	private Alumno receptor;
	
	@JoinColumn(name = "usuario_emi")
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa emisor;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public Alumno getReceptor() {
		return receptor;
	}

	public void setReceptor(Alumno receptor) {
		this.receptor = receptor;
	}

		
	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Empresa getEmisor() {
		return emisor;
	}

	public void setEmisor(Empresa emisor) {
		this.emisor = emisor;
	}
}