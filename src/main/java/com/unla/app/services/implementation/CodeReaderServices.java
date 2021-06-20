package com.unla.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.app.entities.CodeReaders;
import com.unla.app.repositories.ICodeReaderRepository;
import com.unla.app.services.ICodeReaderService;

@Service("codereaderService")
public class CodeReaderServices implements ICodeReaderService {

	@Autowired
	@Qualifier("codereaderRepository")
	private ICodeReaderRepository iqrRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<CodeReaders> findAll() {		
		return (List<CodeReaders>) iqrRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public CodeReaders findOne(Long id) {
		return iqrRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<CodeReaders> findAll(Pageable pageable) {
		return iqrRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(CodeReaders qr) {
		iqrRepository.save(qr);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iqrRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public CodeReaders findOneByCode(String code) {
		return iqrRepository.findByCode(code).orElse(null);
	}

	@Override
	@Transactional(readOnly = true)
	public CodeReaders findOneByPermisoId(long permisoId) {
		return iqrRepository.findOneByPermisoId(permisoId).orElse(null);
	}

}
