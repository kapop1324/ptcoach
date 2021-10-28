package com.pt.model.service;

import java.util.List;

import com.pt.domain.Exercise;
import com.pt.domain.ExerciseRes;

public interface ExerciseService {
	
	public List<ExerciseRes> exercise_list() throws Exception;

}
