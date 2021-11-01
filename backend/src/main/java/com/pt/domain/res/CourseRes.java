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
public class CourseRes {
	
	private String coursename;
	private String exercisename;
	private String path;

	
	@Builder
	public CourseRes(Course c, Exercise ex, ExerciseImage exi) {
		
		this.coursename = c.getCoursename();
		this.exercisename = c.getExercisename();
		this.path = exi.getPath();

	}

}
