package com.pt.model.dao.qdsl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;

import com.pt.domain.Course;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;
import com.pt.domain.QCourse;
import com.pt.domain.QExercise;
import com.pt.domain.QExerciseDetailedRecord;
import com.pt.domain.QExerciseImage;
import com.pt.domain.QExerciseRecord;
import com.pt.domain.res.AccuracyRes;
import com.pt.domain.res.AccuracyResFin;
import com.pt.domain.res.AccuracyTransitionRes;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.PartRes;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.ConstantImpl;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.StringTemplate;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class DashboardDaoQdsl {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QCourse qCourse = QCourse.course;
	QExercise qExercise = QExercise.exercise;
	QExerciseRecord qExerciseRecord = QExerciseRecord.exerciseRecord;
	
	
	//전체 운동 시간
	public int all_time(String email) {
		
		List<Integer> time = jpaQueryFactory.select(qExerciseRecord.time.sum())
				.from(qExerciseRecord).where(qExerciseRecord.useremail.eq(email))
				.groupBy(qExerciseRecord.useremail)
				.fetch();
		
		int all_time = time.get(0);
		
		return all_time;
	}
	
	
	//한달 운동 시간
	public int month_time(String email) {
		
		Date now = new Date();
		
		Calendar mon = Calendar.getInstance();
		mon.add(Calendar.MONTH , -1);
		Date start = mon.getTime();

		List<Integer> time = jpaQueryFactory.select(qExerciseRecord.time.sum())
				.from(qExerciseRecord).where(qExerciseRecord.useremail.eq(email)
						.and(qExerciseRecord.date.between(start, now)))
				.groupBy(qExerciseRecord.useremail)
				.fetch();
		int month_time = time.get(0);
			
		return month_time;
	}
	
	
	//부위별 분포도
	public List<Integer> part(String email) {
		
		List<Tuple> part = 
				jpaQueryFactory.select(qExercise.part, qExerciseRecord.time.sum())
				.from(qExerciseRecord).innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
				.innerJoin(qExercise).on(qCourse.exerciseidx.eq(qExercise.idx))
				.where(qExerciseRecord.useremail.eq(email))
				.groupBy(qExercise.part)
				.fetch();
		
		Stack<PartRes> stack = new Stack<PartRes>();
		
		for(Tuple t : part) {
			String part1 = t.get(0, String.class);
			int time = t.get(1, int.class);
			stack.add(new PartRes(part1,time));
		}
		
		List<PartRes> tmp = new ArrayList<PartRes>();
		List<Integer> res = new ArrayList<Integer>();
		String order[] = {"상체","하체","전신"};
		
		while(!stack.isEmpty()) {
			
			tmp.add(stack.pop());
			
		}
		
		for(int i = 0; i < 3; i++) {
			
			boolean flag = false;
			
			for(int j = 0; j < tmp.size(); j++) {
				
				if(tmp.get(j).getPart().equals(order[i])) {
					res.add(tmp.get(j).getTime());
					flag = true;
				}
				
			}
			
			if(flag == false) {
				res.add(0);
			}
			
		}
		
		return res;

	}
	
	//운동별 최근정확도 및 평균정확도
	public List<AccuracyResFin> accuracy(String email){
		
		List<Tuple> whole_list = jpaQueryFactory.select(qCourse.exercisename,qExerciseRecord.accuracy.avg())
				.from(qExerciseRecord)
				.innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
				.where(qExerciseRecord.useremail.eq(email))
				.groupBy(qCourse.exercisename)
				.fetch();
		
		Stack<AccuracyRes> whole_stack = new Stack<AccuracyRes>();
		
		for(Tuple t : whole_list) {
			String part1 = t.get(0, String.class);
			double time = t.get(1, double.class);
			int time1 = (int) time;
			whole_stack.add(new AccuracyRes(part1,time1));
		}
		
		List<AccuracyRes> whole_res = new ArrayList<AccuracyRes>();
		
		while(!whole_stack.isEmpty()) {
			whole_res.add(whole_stack.pop());
		} 
		
		List<Tuple> recent_list = jpaQueryFactory.select(qCourse.exercisename,qExerciseRecord.accuracy.avg(),qExerciseRecord.date.max())
				.from(qExerciseRecord)
				.innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
				.where(qExerciseRecord.useremail.eq(email).and(qExerciseRecord.date.eq(jpaQueryFactory.select(qExerciseRecord.date.max()).from(qExerciseRecord).where(qExerciseRecord.useremail.eq(email)))))
				.groupBy(qCourse.exercisename)
				.fetch();
		
		Stack<AccuracyRes> recent_stack = new Stack<AccuracyRes>();
		
		for(Tuple t : recent_list) {
			String part1 = t.get(0, String.class);
			double time = t.get(1, double.class);
			int time1 = (int) time;
			Date date = t.get(2, Date.class);
			SimpleDateFormat fm = new SimpleDateFormat("MM/dd");
			String to = fm.format(date);
			recent_stack.add(new AccuracyRes(part1,time1,to));
		}
		
		List<AccuracyRes> recent_res = new ArrayList<AccuracyRes>();
		
		while(!recent_stack.isEmpty()) {
			recent_res.add(recent_stack.pop());
		}
		
		List<AccuracyResFin> res = new ArrayList<AccuracyResFin>();
		
		for(int i = 0; i < whole_res.size(); i++) {
			for(int j = 0; j < recent_res.size(); j++) {
				if(whole_res.get(i).getExercisename().equals(recent_res.get(j).getExercisename())) {
					res.add(new AccuracyResFin(whole_res.get(i).getExercisename(),whole_res.get(i).getAccuracy(), recent_res.get(j).getAccuracy(),recent_res.get(j).getDate()));
				}
			}
		}
		
		List<String> exercise_name = jpaQueryFactory.select(qExercise.name).from(qExercise).fetch();
		List<AccuracyResFin> resf = new ArrayList<AccuracyResFin>();
		
		for(int i = 0; i < exercise_name.size(); i++) {
			
			for(int j = 0; j < res.size(); j++) {
				if(exercise_name.get(i).equals(res.get(j).getExercisename())) {
					resf.add(res.get(j));
					break;
				}
			}
			
		}
		
		return resf;

	}
	

	//정확도 변경추이
	public List<AccuracyTransitionRes> accuracy_transition(String email){
		
		List<String> exercise_name = jpaQueryFactory.select(qExercise.name).from(qExercise).fetch();

		List<Tuple> accuracy_list = jpaQueryFactory.select(qCourse.exercisename,qExerciseRecord.accuracy.avg(),qExerciseRecord.date)
				.from(qExerciseRecord)
				.innerJoin(qCourse).on(qExerciseRecord.courseidx.eq(qCourse.idx))
				.where(qExerciseRecord.useremail.eq(email))
				.groupBy(qExerciseRecord.date, qCourse.exercisename)
				.orderBy(qExerciseRecord.date.desc())
				.fetch();
		
		Stack<AccuracyRes> accuracy_stack = new Stack<AccuracyRes>();
		
		for(Tuple t : accuracy_list) {
			String ex = t.get(0, String.class);
			
			double rate1 = t.get(1, double.class);
			int rate = (int) rate1;
			Date date = t.get(2, Date.class);
			SimpleDateFormat fm = new SimpleDateFormat("MM/dd");
			String to = fm.format(date);
			accuracy_stack.add(new AccuracyRes(ex,rate,to));
		}
		
		List<AccuracyRes> accuracy_res = new ArrayList<AccuracyRes>();
		
		while(!accuracy_stack.isEmpty()) {
			accuracy_res.add(accuracy_stack.pop());
		}
		
		List<Integer> accuracy = new ArrayList<Integer>();
		List<String> date = new ArrayList<String>();
		
		List<AccuracyTransitionRes> res = new ArrayList<AccuracyTransitionRes>();
		
		for(int i = 0; i < exercise_name.size(); i++) {
			int cnt = 0;
			accuracy = new ArrayList<Integer>();
			date = new ArrayList<String>();
			for(int j = 0; j < accuracy_res.size(); j++) {
				
				if(exercise_name.get(i).equals(accuracy_res.get(j).getExercisename())) {
					accuracy.add(accuracy_res.get(j).getAccuracy());
					date.add(accuracy_res.get(j).getDate());
				}
				
				if(cnt == 30 || j == accuracy_res.size()-1) {
					if(accuracy.size() > 0) {
						
						res.add(new AccuracyTransitionRes(exercise_name.get(i),accuracy,date));
					}
					
					
					break;
				}
				
			}
		}
		
		return res;
	}
	


}
