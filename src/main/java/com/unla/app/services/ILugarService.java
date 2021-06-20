package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.unla.app.entities.Lugares;

public interface ILugarService {
	
	public List<Lugares> findAll();
	
	public Page<Lugares> findAll(Pageable pageable);
	
	public void save(Lugares lugares);
	
	public Lugares findByCodigoPostal(String codigoPostal);

}
