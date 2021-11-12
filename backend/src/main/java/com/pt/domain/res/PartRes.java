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
public class PartRes {
	
	
	private String part;
	private int time;

	
	@Builder
	public PartRes(String part, int time) {
		
		this.part = part;
		this.time = time;
		

	}

	
}
