package com.pt.domain.res;

import com.pt.domain.Course;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseRes {
	
	private String coursename;
	private String exercisename;
	private String path;
	
	
	@Builder
	public CourseRes( String exercisename, String path) {

		this.exercisename = exercisename;
		this.path = path;

	}

	
	@Builder
	public CourseRes(String coursename, String exercisename, String path) {
		
		this.coursename = coursename;
		this.exercisename = exercisename;
		this.path = path;

	}
	
	
}
