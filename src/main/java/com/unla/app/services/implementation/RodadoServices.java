package com.unla.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.unla.app.entities.Rodados;
import com.unla.app.repositories.IRodadoRepository;
import com.unla.app.services.IRodadoService;

@Service("rodadoService")
public class RodadoServices implements IRodadoService  {
	
	@Autowired
	@Qualifier("rodadoRepository")
	private IRodadoRepository iRodadoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Rodados> findAll() {
		return (List<Rodados>) iRodadoRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Rodados> findAll(Pageable pageable) {
		return iRodadoRepository.findAll(pageable);
	}

	@Override
	public void save(Rodados rodados) {
		iRodadoRepository.save(rodados);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Rodados findOne(long id) {
		return iRodadoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(long id) {
		iRodadoRepository.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Rodados findOneByDominio(String dominio) {
		return iRodadoRepository.findByDominio(dominio).orElse(null);
	}
	

}
