CREATE DATABASE bolsacds;

USE bolsacds;

CREATE TABLE habilidad(
	id int PRIMARY KEY AUTO_INCREMENT,
    descripcion varchar(50),
    tipo enum('T','G')
);

CREATE TABLE tipo_usuario(
	id int PRIMARY KEY AUTO_INCREMENT,
    descripcion varchar(70)
);

CREATE TABLE certificacion(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(255)
);

CREATE TABLE departamento(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(200)
);

CREATE TABLE municipio(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(200),
    id_depart int,
    FOREIGN KEY (id_depart) REFERENCES departamento (id)
);


CREATE TABLE tipo_documento(
	id int PRIMARY KEY AUTO_INCREMENT,
    descripcion varchar(255)
);

CREATE TABLE usuario(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(100),
    apellido varchar(75),
    email varchar(255),
    clave varchar(255),
    estado enum('Activo','Inactivo'),
    telefono varchar(9),
    celular varchar(9),
    direccion VARCHAR(255),
    token varchar(255),
    fecha date,
    id_tipo int,
    id_municipio int,
    FOREIGN KEY (id_tipo) REFERENCES tipo_usuario (id),
    FOREIGN KEY (id_municipio) REFERENCES municipio (id)
);


CREATE TABLE documento(
	id int PRIMARY KEY AUTO_INCREMENT,
    ruta varchar(255),
    id_usuario int,
    id_tipoDoc int,
    FOREIGN KEY (id_tipoDoc) REFERENCES tipo_documento (id),
    FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);


CREATE TABLE email(
	id int PRIMARY KEY AUTO_INCREMENT,
    asunto varchar(255),
    contenido text,
    usuario_recep INT,
    usuario_emi int,
    FOREIGN KEY (usuario_recep) REFERENCES usuario (id),
    FOREIGN KEY (usuario_emi) REFERENCES usuario (id)
);


CREATE TABLE detalle_habilidades(
	id int PRIMARY KEY AUTO_INCREMENT,
    id_habilidad int,
    id_usuario int,
    FOREIGN KEY (id_habilidad) REFERENCES habilidad (id),
    FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);

CREATE TABLE educacion(
	id int PRIMARY KEY AUTO_INCREMENT,
    universidad varchar(255),
    carrera varchar(255),
    id_certificacion int,
    id_usuario int,
	FOREIGN KEY (id_usuario) REFERENCES usuario (id),
    FOREIGN KEY (id_certificacion) REFERENCES certificacion (id)
);


CREATE TABLE clave_temporal(
	id int PRIMARY KEY AUTO_INCREMENT,
    clavet varchar(255),
    estado enum('Activo','Inactivo'),
    id_usuario int,
	FOREIGN KEY (id_usuario) REFERENCES usuario (id)
);
