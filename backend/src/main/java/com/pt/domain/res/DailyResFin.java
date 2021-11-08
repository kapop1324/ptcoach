package com.pt.domain.res;

import java.util.Date;
import java.util.List;

import com.pt.domain.Course;
import com.pt.domain.Exercise;
import com.pt.domain.ExerciseImage;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DailyResFin {
	
	private String course_name;
	private List<DailyRes> exercises;
	

	
	@Builder
	public DailyResFin(String course_name, List<DailyRes> exercises) {
		
		this.course_name = course_name;
		this.exercises = exercises;

	}

	
}
