package com.pt.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pt.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserDao extends JpaRepository<User, String>{
	
	public User findByEmail(String email);
	
	public long deleteByEmail(String email);


}
