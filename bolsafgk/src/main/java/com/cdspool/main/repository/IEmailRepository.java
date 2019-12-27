package com.cdspool.main.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cdspool.main.model.Email;

@Repository
public interface IEmailRepository extends CrudRepository<Email, Integer>{
	public List<Email> findByOrderByIdDesc();
}