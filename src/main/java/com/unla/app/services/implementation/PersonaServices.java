package com.unla.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.app.entities.Personas;
import com.unla.app.repositories.IPersonaRepository;
import com.unla.app.services.IPersonaService;

@Service("personaService")
public class PersonaServices implements IPersonaService {

	@Autowired
	@Qualifier("personaRepository")
	private IPersonaRepository iPersonaRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Personas> findAll() {		
		return (List<Personas>) iPersonaRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Personas findOne(Long id) {
		return iPersonaRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Personas findOneByDNI(int dni) {
		return iPersonaRepository.findByDni(dni).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Personas> findAll(Pageable pageable) {
		return iPersonaRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Personas persona) {
		iPersonaRepository.save(persona);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iPersonaRepository.deleteById(id);
	}

}