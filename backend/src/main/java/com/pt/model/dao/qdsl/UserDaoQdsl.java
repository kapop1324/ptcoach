package com.pt.model.dao.qdsl;


import com.pt.domain.QUser;
import com.pt.domain.User;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoQdsl{
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;

 
	QUser qUser = QUser.user;
	
	public User login(User user) {
		
		User login = jpaQueryFactory.select(qUser).from(qUser).where(qUser.email.eq(user.getEmail())).fetchOne();
		
		return login;
		
	}

    
    public User userinfo(String email) {
    	
    	User userinfo = jpaQueryFactory.select(qUser).from(qUser).where(qUser.email.eq(email)).fetchOne();
    	
    	return userinfo;
    	
    }
    
    public String idcheck(String email) {
    	
    	String idcheck = jpaQueryFactory.select(qUser.email).from(qUser).where(qUser.email.eq(email)).fetchOne();
    	
    	return idcheck;
    }
    
    public long leave(String email) {
    	
    	return jpaQueryFactory.delete(qUser).where(qUser.email.eq(email)).execute();
    }
    


}
