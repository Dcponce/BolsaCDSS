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
@Table(name = "documento")
public class Documento {
	 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String ruta;

	@JoinColumn(name = "id_usuario")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuarios id_usuario;

	@JoinColumn(name = "id_tipodoc")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private TipoDocumento id_tipodoc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRuta() {
		return ruta;
	}

	public void setRuta(String ruta) {
		this.ruta = ruta;
	}

	public Usuarios getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Usuarios id_usuario) {
		this.id_usuario = id_usuario;
	}

	public TipoDocumento getId_tipoDoc() {
		return id_tipodoc;
	}

	public void setId_tipoDoc(TipoDocumento id_tipodoc) {
		this.id_tipodoc = id_tipodoc;
	}

}
