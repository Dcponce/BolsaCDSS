package com.cdspool.main.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String email;

	@JoinColumn(name = "id_credencial")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Credencial credencial;

	private String clave;

	@JoinColumn(name = "id_tipo")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private TipoUsuario id_tipo;

	private Boolean estado;

	private String activo;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Credencial getCredencial() {
		return credencial;
	}

	public void setCredencial(Credencial credencial) {
		this.credencial = credencial;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public TipoUsuario getId_tipo() {
		return id_tipo;
	}

	public void setId_tipo(TipoUsuario id_tipo) {
		this.id_tipo = id_tipo;
	}

	public Boolean getEstado() {
		return estado;
	}

	public void setEstado(Boolean estado) {
		this.estado = estado;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}