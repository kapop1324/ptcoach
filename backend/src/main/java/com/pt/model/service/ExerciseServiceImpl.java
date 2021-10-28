package com.pt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.domain.Exercise;
import com.pt.domain.ExerciseRes;
import com.pt.model.dao.ExerciseDao;
import com.pt.model.dao.qdsl.ExerciseDaoQdsl;

@Service
public class ExerciseServiceImpl implements ExerciseService {
	
	@Autowired
	ExerciseDao exercisedao;
	
	@Autowired
	ExerciseDaoQdsl exercisedaoqdsl;

	@Override
	public List<ExerciseRes> exercise_list() throws Exception {
		
		List<ExerciseRes> list = exercisedaoqdsl.exercise_list();
		
		return list;
	}
	
	
	
}
