package com.cdspool.main.model;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "alumno")
public class Alumno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String nombre;

	private String apellido;

	private String telefono;

	private String celular;

	private String direccion;

	@JoinColumn(name = "proyecto")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Centros proyecto;

	@Temporal(TemporalType.DATE)
	private Calendar fecha;

	@JoinColumn(name = "id_municipio")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Municipios id_municipio;

	@JoinColumn(name = "id_usuario")
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	private Usuario usuario;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public Municipios getId_municipio() {
		return id_municipio;
	}

	public void setId_municipio(Municipios id_municipio) {
		this.id_municipio = id_municipio;
	}

	public Usuario getId_usuario() {
		return usuario;
	}

	public void setId_usuario(Usuario id_usuario) {
		this.usuario = id_usuario;
	}

	public Centros getProyecto() {
		return proyecto;
	}

	public void setProyecto(Centros proyecto) {
		this.proyecto = proyecto;
	}

}
