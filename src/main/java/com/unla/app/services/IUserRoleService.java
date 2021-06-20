package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.app.entities.UsersRole;

public interface IUserRoleService {

	public List<UsersRole> findAll();

	public Page<UsersRole> findAll(Pageable pageable);

	public void save(UsersRole user);

	public UsersRole findOne(Long id);

	public void delete(Long id);

}
