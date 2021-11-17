package com.pt.model.dao.qdsl;


import com.pt.domain.QCourse;
import com.pt.domain.QExercise;
import com.pt.domain.QExerciseRecord;
import com.pt.domain.QUser;
import com.pt.domain.User;
import com.pt.domain.res.DailyRes;
import com.pt.domain.res.DailyResFin;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;



@Repository
public class UserDaoQdsl{
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;

 
	QUser qUser = QUser.user;
	QCourse qCourse = QCourse.course;
	QExercise qExercise = QExercise.exercise;
	QExerciseRecord qExerciseRecord = QExerciseRecord.exerciseRecord;
	
	
	public User login(User user) {
		
		User login = jpaQueryFactory.select(qUser).from(qUser).where(qUser.email.eq(user.getEmail()).and(qUser.password.eq(user.getPassword()))).fetchOne();
		
		return login;
		
	}

	public long update(User user){

		return jpaQueryFactory.update(qUser)
				.set(qUser.name, user.getName())
				.set(qUser.password, user.getPassword())
				.set(qUser.height, user.getHeight())
				.set(qUser.weight, user.getWeight())
				.set(qUser.gender, user.getGender())
				.where(qUser.email.eq(user.getEmail()))
				.execute();

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
    
    public List<String> dailyresult(String email){
    	
    	List<Date> day = jpaQueryFactory.select(qExerciseRecord.date)
    			.from(qExerciseRecord)
    			.where(qExerciseRecord.useremail.eq(email))
    			.groupBy(qExerciseRecord.date)
    			.fetch();

    	List<String> res = new ArrayList<String>();

    	for(int i = 0; i < day.size(); i++) {
    		SimpleDateFormat fm = new SimpleDateFormat("yyyy-MM-dd");
    		Calendar cal = Calendar.getInstance();
    		cal.setTime(day.get(i));
    		cal.add(Calendar.DATE, -1);
			String to = fm.format(cal.getTime());
			res.add(to);
    	}
    	
    	return res;
    }
    
  public List<DailyResFin> dailydetailresult(String email, String date) throws ParseException{
	  	
	  SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date to = transFormat.parse(date);
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(to);
		//cal.add(Calendar.DATE, -1);
		Date from = cal.getTime();

    	List<String> course_list = jpaQueryFactory.select(qCourse.coursename)
    			.from(qExerciseRecord)
    			.innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
    			.where(qExerciseRecord.useremail.eq(email).and(qExerciseRecord.date.loe(to)).and(qExerciseRecord.date.gt(from)))
    			.groupBy(qCourse.coursename)
    			.fetch();
   
    	List<Tuple> tuple = jpaQueryFactory.select(qCourse.coursename, qCourse.exercisename, qExerciseRecord.time.sum(), qExerciseRecord.accuracy.avg(), qCourse.set.sum())
    			.from(qExerciseRecord)
    			.innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
    			.where(qExerciseRecord.useremail.eq(email).and(qExerciseRecord.date.loe(to)).and(qExerciseRecord.date.gt(from)))
    			.groupBy(qCourse.coursename, qCourse.exercisename)
    			.fetch();
    	
    	List<DailyRes> tmp = new ArrayList<DailyRes>();
    	Stack<DailyRes> stack = new Stack<DailyRes>();
    	
    	for(Tuple t : tuple) {
    		
    		String coursename = t.get(0, String.class);
    		String exercisename = t.get(1, String.class);
    		int time = t.get(2, Integer.class);
    		double accuracy1 = t.get(3, Double.class);
    		int accuracy = (int) accuracy1;
    		int set = t.get(4, Integer.class);
    		stack.add(new DailyRes(coursename, exercisename, time, accuracy, set));
    		
    	}
    	
    	while(!stack.isEmpty()) {
    		tmp.add(stack.pop());
    	}
    	
    	List<DailyRes> d = new ArrayList<DailyRes>();
    	List<DailyResFin> res = new ArrayList<DailyResFin>();
    	
    	for(int i = 0; i < course_list.size(); i++) {
    		d = new ArrayList<DailyRes>();
    		for(int j = 0; j < tmp.size(); j++) {
    			
    			if(course_list.get(i).equals(tmp.get(j).getCoursename())) {
    				d.add(tmp.get(j));
    			}
    			
    		}
    		res.add(new DailyResFin(course_list.get(i), d));
    	}
    
    	return res;
    }
    


}
