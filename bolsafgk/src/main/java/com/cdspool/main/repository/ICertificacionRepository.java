package com.cdspool.main.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Certificacion;

@Repository
public interface ICertificacionRepository extends CrudRepository<Certificacion, Integer> {

}
