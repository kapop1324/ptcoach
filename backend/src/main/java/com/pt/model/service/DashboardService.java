package com.pt.model.service;

import java.util.List;

import com.pt.domain.res.AccuracyRes;
import com.pt.domain.res.AccuracyResFin;
import com.pt.domain.res.AccuracyTransitionRes;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.PartRes;

public interface DashboardService {
	
	public int all_time(String email) throws Exception;
	
	public int month_time(String email) throws Exception;
	
	public List<Integer> partdist(String email) throws Exception;
	
	public List<AccuracyResFin> accuracy(String email) throws Exception;
	
	public List<AccuracyTransitionRes> accuracy_transition(String email) throws Exception;
}
