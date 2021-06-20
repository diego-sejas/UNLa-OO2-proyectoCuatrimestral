package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.app.entities.CodeReaders;

public interface ICodeReaderService {

	public List<CodeReaders> findAll();

	public Page<CodeReaders> findAll(Pageable pageable);

	public void save(CodeReaders qr);

	public CodeReaders findOne(Long id);

	public void delete(Long id);

	public CodeReaders findOneByCode(String code);

	public CodeReaders findOneByPermisoId(long permisoId);

}
