package com.cdspool.main.shared;

import java.io.Serializable;

import com.cdspool.main.model.Usuario;

public class TemporalDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String clavet;
	
	private Usuario id_usuario;

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

	public Usuario getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.id_usuario = id_usuario;
	}
}