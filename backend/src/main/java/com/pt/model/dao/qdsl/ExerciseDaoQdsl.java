package com.pt.model.dao.qdsl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;
import com.pt.domain.QExercise;
import com.pt.domain.QExerciseImage;
import com.pt.domain.res.ExerciseRes;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ExerciseDaoQdsl {
	
	@Autowired
	private JPAQueryFactory jpaQueryFactory;
	
	QExercise qExercise = QExercise.exercise;
	QExerciseImage qExerciseImage = QExerciseImage.exerciseImage;
	Stack<ExerciseRes> stack = new Stack<ExerciseRes>();
	
	public List<ExerciseRes> exercise_list(){
		
		List<Tuple> exercise_list = jpaQueryFactory.select(qExercise, qExerciseImage)
				.from(qExercise).innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
				.where(qExerciseImage.step.eq(0))
				.orderBy().fetch();
		
		List<ExerciseRes> res = new ArrayList<ExerciseRes>();
		
		for(Tuple t : exercise_list) {
			Exercise ex = t.get(0,Exercise.class);
			ExerciseImage exi = t.get(1,ExerciseImage.class);
			stack.add(new ExerciseRes(ex,exi));
		}
		
		while(!stack.isEmpty()) {
			res.add(stack.pop());
		}
		
		return res;
	}
	
	public List<ExerciseRes> exercise_detail_list(int idx){
		
		List<Tuple> exercise_list = jpaQueryFactory.select(qExercise, qExerciseImage)
				.from(qExercise).innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
				.where(qExerciseImage.step.ne(0).and(qExercise.idx.eq(idx)))
				.orderBy(qExerciseImage.step.desc()).fetch();
		
		List<ExerciseRes> res = new ArrayList<ExerciseRes>();
		
		for(Tuple t : exercise_list) {
			Exercise ex = t.get(0,Exercise.class);
			ExerciseImage exi = t.get(1,ExerciseImage.class);
			stack.add(new ExerciseRes(ex,exi));
		}
		
		while(!stack.isEmpty()) {
			res.add(stack.pop());
		}
		
		return res;
	}

}
