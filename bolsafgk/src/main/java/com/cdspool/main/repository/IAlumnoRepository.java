package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Alumno;
import com.cdspool.main.model.Usuario;

@Repository
public interface IAlumnoRepository extends CrudRepository<Alumno, Integer> {

	// Query para listar alumnos por habilidad, departamento y certificacion
	@Query(
			value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi OR m.id_depart = :depart OR dh.id_habilidad IN (:habi) OR c.id = :centro) GROUP BY 1 ORDER BY 1;", 
			nativeQuery = true)
	List<Alumno> findAllNative(@Param("certi") Integer certi, @Param("depart") Integer depto, @Param("habi") String habi, @Param("centro") Integer centro);
	
	public Alumno findByUsuario(Usuario usuario);

}
