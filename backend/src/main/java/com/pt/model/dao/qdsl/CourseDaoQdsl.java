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
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CourseDaoQdsl {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QCourse qCourse = QCourse.course;
	QExercise qExercise = QExercise.exercise;
	QExerciseImage qExerciseImage = QExerciseImage.exerciseImage;
	
	
	public List<CourseRes> course_list() {
		
		Stack<CourseRes> stack = new Stack<CourseRes>();
		
		List<Tuple> course_list = jpaQueryFactory.select(qCourse,qExercise,qExerciseImage)
				.from(qCourse).innerJoin(qExercise).on(qCourse.exercisename.eq(qExercise.name))
				.innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
				.where(qExerciseImage.step.eq(0)).orderBy(qCourse.coursename.desc())
				.fetch();
		
		List<CourseRes> res = new ArrayList<CourseRes>();
		
		for(Tuple t : course_list) {
			
			Course c = t.get(0,Course.class);
			Exercise ex = t.get(1,Exercise.class);
			ExerciseImage exi = t.get(2,ExerciseImage.class);
			stack.add(new CourseRes(c,ex,exi));
			
		}
		
		while(!stack.isEmpty()) {
			res.add(stack.pop());
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
