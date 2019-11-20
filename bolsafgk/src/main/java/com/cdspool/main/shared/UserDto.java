package com.cdspool.main.shared;

import java.io.Serializable;

import com.cdspool.main.model.Credencial;
import com.cdspool.main.model.TipoUsuario;

public class UserDto implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;

	private String email;
	
	private Credencial id_credencial;

	private String clave;
	
	private String password;

	private TipoUsuario id_tipo;

	private Boolean estado;

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

	public Credencial getId_credencial() {
		return id_credencial;
	}

	public void setId_credencial(Credencial id_credencial) {
		this.id_credencial = id_credencial;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
}