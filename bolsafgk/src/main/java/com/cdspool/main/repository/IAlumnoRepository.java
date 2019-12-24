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

//	@Query(
//			value = "SELECT a FROM Alumno a INNER JOIN Usuario u ON u.id = a.id_usuario INNER JOIN Educacion e ON e.id_usuario = u.id INNER JOIN Municipio m ON a.id_municipio = m.id INNER JOIN DetalleHabilidades dh ON dh.id_usuario = u.id WHERE (e.certificacion = :certi OR m.departamento = :depto OR dh.habilidad IN (:habi)) GROUP BY 1 ORDER BY 1;", 
//			nativeQuery = true)
//	List<Alumno> findAlumnoByCertificacionOrDepartamentoOrHabilidadNamedParamsNative(@Param("certi") Integer certi, @Param("depto") Integer depto, @Param("habilidad") String habi);

	public Alumno findByUsuario(Usuario usuario);

}
