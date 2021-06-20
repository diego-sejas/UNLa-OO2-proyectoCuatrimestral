package com.unla.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.app.entities.UsersRole;
import com.unla.app.repositories.IUserRoleRepository;
import com.unla.app.services.IUserRoleService;

@Service("userRoleService")
public class UserRoleServices implements IUserRoleService {

	
	@Autowired
	@Qualifier("userRoleRepository")
	private IUserRoleRepository iUserRoleRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<UsersRole> findAll() {
		
		return (List<UsersRole>) iUserRoleRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public UsersRole findOne(Long id) {
		return iUserRoleRepository.findById(id).orElse(null);
	}
		
	@Override
	@Transactional(readOnly = true)
	public Page<UsersRole> findAll(Pageable pageable) {
		return iUserRoleRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(UsersRole user) {
		iUserRoleRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iUserRoleRepository.deleteById(id);
	}

}
