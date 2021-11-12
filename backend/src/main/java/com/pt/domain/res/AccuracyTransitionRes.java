package com.pt.domain.res;

import java.util.Date;
import java.util.List;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AccuracyTransitionRes {
	
	private String exercisename;
	private List<Integer> accuracy;
	private List<String> date;

	
	@Builder
	public AccuracyTransitionRes(String exercisename, List<Integer> accuracy, List<String> date) {
		
		this.exercisename = exercisename;
		this.accuracy = accuracy;
		this.date = date;
		
	}
}
