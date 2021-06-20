package com.unla.app.services.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unla.app.entities.Users;
import com.unla.app.repositories.IUserRepository;
import com.unla.app.services.IUserService;

@Service("userService")
public class UserServices implements IUserService {
	
	@Autowired
	@Qualifier("userRepository")
	private IUserRepository iuserRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Users> findAll() {
		
		return (List<Users>) iuserRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Users findOne(Long id) {
		return iuserRepository.findById(id).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Users findOneByEmail(String email) {
		return iuserRepository.findByEmail(email).orElse(null);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<Users> findAll(Pageable pageable) {
		return iuserRepository.findAll(pageable);
	}

	@Override
	@Transactional
	public void save(Users user) {
		iuserRepository.save(user);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		iuserRepository.deleteById(id);
	}

}
