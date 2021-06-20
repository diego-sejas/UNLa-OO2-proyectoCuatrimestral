package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.app.entities.Personas;

public interface IPersonaService {

	public List<Personas> findAll();

	public Page<Personas> findAll(Pageable pageable);

	public void save(Personas persona);

	public Personas findOne(Long id);

	public void delete(Long id);

	public Personas findOneByDNI(int dni);

}