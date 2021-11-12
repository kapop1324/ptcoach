package com.pt.domain.res;

import javax.persistence.Column;
import javax.persistence.Id;

import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseRes {
	
	private String exercisename;
	
	private String path;
	
	private int step;
	
	private String desc;
	
	private String part;
	
	private String category;
	
	private int idx;


	public ExerciseRes(Exercise ex, ExerciseImage exi) {
		
		this.idx = ex.getIdx();
		
		this.exercisename = ex.getName();
		
		this.path = exi.getPath();
		
		this.step = exi.getStep();
		 
		this.desc = exi.getDesc();
		 
		this.part = ex.getPart();
		 
		this.category = ex.getCategory();
		

	}
}
