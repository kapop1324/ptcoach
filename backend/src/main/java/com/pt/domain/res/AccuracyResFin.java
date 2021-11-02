package com.pt.domain.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccuracyResFin {
	
	private String exercisename;
	private int wholeaccuracy;
	private int recentaccuracy;
	
	@Builder
	public AccuracyResFin(String exercisename, int recentaccuracy, int wholeaccuracy) {
		
		this.exercisename = exercisename;
		this.wholeaccuracy = wholeaccuracy;
		this.recentaccuracy = recentaccuracy;
		
	}


}
