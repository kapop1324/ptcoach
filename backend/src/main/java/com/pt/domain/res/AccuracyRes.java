package com.pt.domain.res;

import java.util.Date;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class AccuracyRes {
	

	private String exercisename;
	private int accuracy;
	private String date;
	
	
	@Builder
	public AccuracyRes(String exercisename, int accuracy) {
		
		this.exercisename = exercisename;
		this.accuracy = accuracy;
		
	}
	
	@Builder
	public AccuracyRes(String exercisename, int accuracy, String date) {
		
		this.exercisename = exercisename;
		this.accuracy = accuracy;
		this.date = date;
		
	}

}
