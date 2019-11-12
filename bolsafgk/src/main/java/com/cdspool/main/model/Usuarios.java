package com.cdspool.main.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuarios {

	private Integer id;
	private String nombre;
	private String apellido;
	private String email;
	private String clave;
	private String estado;
	private String telefono;
	private String celular;
	private String direccion;
	private String token;
	private Date fecha;
	
}
