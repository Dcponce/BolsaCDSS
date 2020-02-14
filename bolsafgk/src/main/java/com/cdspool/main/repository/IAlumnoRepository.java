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

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad, departamento y certificacion (OR)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi OR m.id_depart = :depart OR dh.id_habilidad IN (:habi) OR c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative(@Param("certi") Integer certi, @Param("depart") Integer depto,
			@Param("habi") String habi, @Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad, departamento y certificacion (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND m.id_depart = :depart AND dh.id_habilidad IN (:habi) AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative2(@Param("certi") Integer certi, @Param("depart") Integer depto,
			@Param("habi") String habi, @Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por departamento, certificacion y centro (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND m.id_depart = :depart AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative3(@Param("certi") Integer certi, @Param("depart") Integer depto,
			@Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad, departamento y certificacion (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND m.id_depart = :depart AND dh.id_habilidad IN (:habi)) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative4(@Param("certi") Integer certi, @Param("depart") Integer depto,
			@Param("habi") String habi);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad, departamento y centro (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (m.id_depart = :depart AND dh.id_habilidad IN (:habi) AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative5(@Param("depart") Integer depto, @Param("habi") String habi,
			@Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad, certificacion y centro (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND dh.id_habilidad IN (:habi) AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative6(@Param("habi") String habi, @Param("centro") Integer centro,
			@Param("certi") Integer certi);

	/********************************************************************************************************************/

	// Query para listar alumnos por departamento y certificacion (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND m.id_depart = :depart) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative7(@Param("certi") Integer certi, @Param("depart") Integer depto);

	/********************************************************************************************************************/

	// Query para listar alumnos por certificacion y centro (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative8(@Param("certi") Integer certi, @Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por centro y departamento (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (m.id_depart = :depart AND c.id = :centro) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative9(@Param("depart") Integer depto, @Param("centro") Integer centro);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad y certificacion (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (e.id_certificacion = :certi AND dh.id_habilidad IN (:habi)) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative10(@Param("habi") String habi, @Param("certi") Integer certi);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad y departamento (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (m.id_depart = :depart AND dh.id_habilidad IN (:habi)) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative11(@Param("habi") String habi, @Param("depart") Integer depto);

	/********************************************************************************************************************/

	// Query para listar alumnos por habilidad y centro (AND)

	@Query(value = "SELECT * FROM alumno a INNER JOIN usuario u ON u.id = a.id_usuario LEFT JOIN educacion e ON e.id_usuario = u.id LEFT JOIN municipio m ON a.id_municipio = m.id LEFT JOIN detalle_habilidades dh ON dh.id_usuario = u.id LEFT JOIN centros c ON c.id = a.proyecto WHERE (c.id = :centro AND dh.id_habilidad IN (:habi)) GROUP BY 1 ORDER BY 1;", nativeQuery = true)
	List<Alumno> findAllNative12(@Param("habi") String habi, @Param("centro") Integer centro);

	/********************************************************************************************************************/

	public Alumno findByUsuario(Usuario usuario);

}
