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
public class DailyRes {
	
	private String coursename;
	private String exercisename;
	private int time;
	private int accuracy;
	private int set;

	
	@Builder
	public DailyRes(String coursename, String exercisename, int time, int accuracy, int set) {
		
		this.coursename = coursename;
		this.exercisename = exercisename;
		this.time = time;
		this.accuracy = accuracy;
		this.set = set;
		

	}

	
}
