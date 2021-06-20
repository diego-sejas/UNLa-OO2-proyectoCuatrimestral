package com.unla.app.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.unla.app.entities.UsersRole;

@Repository("userRoleRepository")
public interface IUserRoleRepository extends PagingAndSortingRepository<UsersRole, Long>{

	// Implementar si se requiere alguna busqueda especifica como usuario por nombre , etc

}