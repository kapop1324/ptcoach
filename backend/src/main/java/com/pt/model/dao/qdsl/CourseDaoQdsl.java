package com.pt.model.dao.qdsl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pt.domain.Course;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;
import com.pt.domain.QCourse;
import com.pt.domain.QExercise;
import com.pt.domain.QExerciseImage;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.CourseResFin;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CourseDaoQdsl {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QCourse qCourse = QCourse.course;
	QExercise qExercise = QExercise.exercise;
	QExerciseImage qExerciseImage = QExerciseImage.exerciseImage;
	
	
	public List<CourseResFin> course_list() {
		
		List<String> course_name_list = jpaQueryFactory.select(qCourse.coursename)
				.from(qCourse)
				.groupBy(qCourse.coursename)
				.orderBy(qCourse.coursename.asc())
				.fetch();
		
		
		Stack<CourseRes> stack = new Stack<CourseRes>();
		
		List<Tuple> tuple = jpaQueryFactory.select(qCourse,qExercise,qExerciseImage)
				.from(qCourse).innerJoin(qExercise).on(qCourse.exercisename.eq(qExercise.name))
				.innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
				.where(qExerciseImage.step.eq(0)).orderBy(qCourse.coursename.asc(), qCourse.step.desc())
				.fetch();
		
		List<CourseRes> course_list = new ArrayList<CourseRes>();
		
		for(Tuple t : tuple) {
			
			Course c = t.get(0,Course.class);
			Exercise ex = t.get(1,Exercise.class);
			ExerciseImage exi = t.get(2,ExerciseImage.class);
			stack.add(new CourseRes(c.getCoursename(),ex.getName(),exi.getPath()));
			
		}
		
		while(!stack.isEmpty()) {
			course_list.add(stack.pop());
		}
		
		
		List<CourseResFin> res = new ArrayList<CourseResFin>();
		List<CourseRes> tmp = new ArrayList<CourseRes>();
		
		for(int i = 0; i < course_name_list.size(); i++) {
			tmp = new ArrayList<CourseRes>();
			for(int j = 0; j < course_list.size(); j++) {
				
				if(course_name_list.get(i).equals(course_list.get(j).getCoursename())) {
					tmp.add(new CourseRes(course_list.get(j).getExercisename(),course_list.get(j).getPath()));
				}
			}
			res.add(new CourseResFin(i+1,course_name_list.get(i),tmp));
		}
		
		
		return res;
	}
	
	public List<CourseDetailRes> course_detail(String coursename) {
		
		Stack<CourseDetailRes> stack = new Stack<CourseDetailRes>();
		
		List<Tuple> course_list = jpaQueryFactory.select(qCourse,qExercise,qExerciseImage)
				.from(qCourse).innerJoin(qExercise).on(qCourse.exercisename.eq(qExercise.name))
				.innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
				.where(qExerciseImage.step.ne(0).and(qCourse.coursename.eq(coursename))).orderBy(qCourse.step.desc(),qExerciseImage.step.desc())
				.fetch();
		
		List<CourseDetailRes> res = new ArrayList<CourseDetailRes>();
		
		for(Tuple t : course_list) {
			
			Course c = t.get(0,Course.class);
			Exercise ex = t.get(1,Exercise.class);
			ExerciseImage exi = t.get(2,ExerciseImage.class);
			stack.add(new CourseDetailRes(c,ex,exi));
			
		}
		
		while(!stack.isEmpty()) {
			res.add(stack.pop());
		}
		
		return res;
	}
	
	
	


}
