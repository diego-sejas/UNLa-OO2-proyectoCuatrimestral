package com.unla.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.unla.app.entities.CodeReaders;

@Repository("codereaderRepository")
public interface ICodeReaderRepository extends PagingAndSortingRepository<CodeReaders, Long>{  	    
    Optional<CodeReaders> findByCode(String code);

    Optional<CodeReaders> findOneByPermisoId(long permisoId);
}
