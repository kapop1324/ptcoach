package com.pt.model.service;

import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.domain.User;
import com.pt.domain.exception.ApplicationException;
import com.pt.domain.res.DailyResFin;
import com.pt.model.dao.UserDao;
import com.pt.model.dao.qdsl.UserDaoQdsl;

import java.util.Date;
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
		
		return userRepositorySupport.login(user);
    	
	}
    
	@Override
	public User join(User user) throws Exception {
	
		return userDao.save(user);
	}

	@Override
	public long update(User user) throws Exception {
		return userRepositorySupport.update(user);
	}

	@Override
	public User finduserEmail(String email) throws Exception {
		
		return userDao.findByEmail(email);
	}
	
	@Override
	public long leave(String email) throws Exception {

		return userDao.deleteByEmail(email);
	}

	@Override
	public List<String> day(String email) throws Exception {
		
		return userRepositorySupport.dailyresult(email);
	}

	@Override
	public List<DailyResFin> daydetail(String email, String date) throws Exception {
		
		return userRepositorySupport.dailydetailresult(email, date);
	}





	

   

}
