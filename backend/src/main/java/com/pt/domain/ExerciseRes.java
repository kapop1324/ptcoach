package com.pt.domain;

import javax.persistence.Column;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRes {
	
	private int idx;
	
	private String name;
	
	private String part;
	
	private String path;
	
	private int step;
	
	private int exerciseidx;
	
	public ExerciseRes(Exercise ex, ExerciseImage exi) {
		
		 this.idx = ex.getIdx();
		
		 this.name = ex.getName();
		
		 this.part = ex.getPart();
		
		 this.path = exi.getPath();
		
		 this.step = exi.getStep();
		
		 this.exerciseidx = exi.getExerciseidx();
	}
}
