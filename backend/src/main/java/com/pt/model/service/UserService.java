package com.pt.model.service;

import org.springframework.transaction.annotation.Transactional;

import com.pt.domain.User;
import com.pt.domain.res.DailyResFin;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface UserService {
	
	public User login(User user) throws Exception;
	
	public User join(User user) throws Exception;
    
    public User finduserEmail(String email) throws Exception;
    
    @Transactional
    public long leave(String email) throws Exception;
    
    public List<String> day(String email) throws Exception;

    public List<DailyResFin> daydetail(String email, String date) throws Exception;


}
