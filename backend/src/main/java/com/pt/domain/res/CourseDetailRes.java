package com.pt.domain.res;

import com.pt.domain.Course;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDetailRes {
	
	private int step;
	private int set;
	private String exercisename;
	private String path;
	private String desc;
	private int exercisestep;
	
	private int idx;
	
	@Builder
	public CourseDetailRes(Course c, Exercise ex, ExerciseImage exi) {
		
		this.exercisename = ex.getName();
		this.step = c.getStep();
		this.set = c.getSet();		
		this.path = exi.getPath();
		this.desc = exi.getDesc();
		this.idx = c.getIdx();
		this.exercisestep = exi.getStep();
	
		
		
		
	}

}
