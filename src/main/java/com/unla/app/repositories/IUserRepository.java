package com.unla.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import com.unla.app.entities.Users;

@Repository("userRepository")
public interface IUserRepository extends PagingAndSortingRepository<Users, Long>{

	// Implementar si se requiere alguna busqueda especifica como usuario por nombre , etc

    Optional<Users> findByEmail(String email);
	    
}