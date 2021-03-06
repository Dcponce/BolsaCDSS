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
@Table(name="clave_temporal")
public class ClaveTemporal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
    //Entidades
	private Integer id;
	private String clavet;
	
	@JoinColumn(name="id_usuario")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;

	//Los Get y Set
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClavet() {
		return clavet;
	}

	public void setClavet(String clavet) {
		this.clavet = clavet;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
}
