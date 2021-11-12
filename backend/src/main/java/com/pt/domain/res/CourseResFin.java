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
	
	private int idx;
	private String name;
	private List<CourseRes> course;
	
	@Builder
	public CourseResFin(int idx, String name, List<CourseRes> course) {
		
		this.idx = idx;
		this.name = name;
		this.course = course;


	}

}
