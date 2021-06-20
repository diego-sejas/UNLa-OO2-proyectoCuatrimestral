package com.unla.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.unla.app.entities.Personas;

@Repository("personaRepository")
public interface IPersonaRepository extends PagingAndSortingRepository<Personas, Long>{

    Personas findById(long idPersona);

    Optional<Personas> findByDni(int dni);
	    
}