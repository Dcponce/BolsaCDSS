package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Alumno;

@Repository
public interface IAlumnoRepository extends CrudRepository<Alumno, Integer>{

}
