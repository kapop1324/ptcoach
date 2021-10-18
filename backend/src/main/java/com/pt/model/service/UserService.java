package com.pt.model.service;

import org.springframework.transaction.annotation.Transactional;

import com.pt.domain.User;

import java.util.List;
import java.util.Map;

public interface UserService {
	
	public User login(User user) throws Exception;
	
	public User join(User user) throws Exception;
    
    public User userinfo(String email) throws Exception;
    
    public String idcheck(String email) throws Exception;
    
    @Transactional
    public long leave(String email) throws Exception;




}
