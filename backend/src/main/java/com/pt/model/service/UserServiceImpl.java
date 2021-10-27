package com.pt.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.domain.User;
import com.pt.domain.exception.ApplicationException;
import com.pt.model.dao.UserDao;
import com.pt.model.dao.qdsl.UserDaoQdsl;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
	
    @Autowired
    private UserDao userDao;
    
    @Autowired
    private UserDaoQdsl userRepositorySupport;
    
    @Override
	public User login(User user) throws Exception {
		
		//return userRepositorySupport.login(user);
    	return userDao.findByEmail(user.getEmail());
	}
    
	@Override
	public User join(User user) throws Exception {
	
		return userDao.save(user);
	}
    
	@Override
	public User finduserEmail(String email) throws Exception {
		
		return userDao.findByEmail(email);
	}
	
	@Override
	public long leave(String email) throws Exception {

		return userDao.deleteByEmail(email);
	}





	

   

}
