package com.pt.domain.res;

import java.util.List;

import com.pt.domain.Course;
import com.pt.domain.res.CourseRes;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CourseResFin {
	
	private String name;
	private List<CourseRes> course;
	
	@Builder
	public CourseResFin(String name, List<CourseRes> course) {
		
		this.name = name;
		this.course = course;


	}

}
