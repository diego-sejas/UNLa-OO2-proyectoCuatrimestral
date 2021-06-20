package com.unla.app.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.app.converters.LugarConverter;
import com.unla.app.entities.Lugares;
import com.unla.app.models.LugarModel;
import com.unla.app.repositories.ILugarRepository;
import com.unla.app.services.ILugarService;

@Service("lugarService")
public class LugaresServices implements ILugarService {

	@Autowired
	@Qualifier("lugarRepository")
	private ILugarRepository iLugarRepository;
		
	@Autowired
	private LugarConverter lugarConverter;
	
	private List<Lugares> lugares = new ArrayList<Lugares>();	
	
	@Override
	@Transactional(readOnly = true)
	public List<Lugares> findAll() {
		return (List<Lugares>) iLugarRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Lugares> findAll(Pageable pageable) {
		return iLugarRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Lugares lugares) {
		iLugarRepository.save(lugares);
	}

	@Override
	@Transactional(readOnly = true)
	public Lugares findByCodigoPostal(String codigoPostal) {
		return iLugarRepository.findByCodigoPostal(codigoPostal).orElse(null);
	}

	public List<LugarModel> getLugares() {
		List<LugarModel> list = new ArrayList<LugarModel>();
		
		for(Lugares lugar : lugares)
			list.add(lugarConverter.entityToModel(lugar));
		
		return list;
	}

}
