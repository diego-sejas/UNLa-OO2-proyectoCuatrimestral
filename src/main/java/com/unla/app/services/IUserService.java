package com.unla.app.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.unla.app.entities.Users;

public interface IUserService {

	public List<Users> findAll();

	public Page<Users> findAll(Pageable pageable);

	public void save(Users user);

	public Users findOne(Long id);

	public void delete(Long id);

	public Users findOneByEmail(String email);

}
