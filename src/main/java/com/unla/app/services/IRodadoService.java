package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unla.app.entities.Rodados;

public interface IRodadoService {
	
	public List<Rodados> findAll();

	public Page<Rodados> findAll(Pageable pageable);

	public void save(Rodados rodados);

	public Rodados findOne(long id);

	public void delete(long id);
	
	public Rodados findOneByDominio(String dominio);

}
