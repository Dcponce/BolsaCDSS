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
@Table(name = "denuncia")
public class Denuncia {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private Integer id;

	private String descripcion;

	private String estado;

	@JoinColumn(name = "id_emisor")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario emisor;

	@JoinColumn(name = "id_reportado")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario reportado;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getEmisor() {
		return emisor;
	}

	public void setEmisor(Usuario emisor) {
		this.emisor = emisor;
	}

	public Usuario getReportado() {
		return reportado;
	}

	public void setReportado(Usuario reportado) {
		this.reportado = reportado;
	}

}
