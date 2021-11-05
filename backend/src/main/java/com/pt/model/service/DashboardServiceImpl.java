package com.pt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.domain.res.AccuracyRes;
import com.pt.domain.res.AccuracyResFin;
import com.pt.domain.res.AccuracyTransitionRes;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.PartRes;
import com.pt.model.dao.CourseDao;
import com.pt.model.dao.qdsl.CourseDaoQdsl;
import com.pt.model.dao.qdsl.DashboardDaoQdsl;

@Service
public class DashboardServiceImpl implements DashboardService {
	
	
	@Autowired
	private DashboardDaoQdsl dashboarddaoqdsl;

	@Override
	public int all_time(String email) throws Exception {
		
		return dashboarddaoqdsl.all_time(email);
	}
	

	@Override
	public int month_time(String email) throws Exception {
		
		return dashboarddaoqdsl.month_time(email);
	}
	

	@Override
	public List<Integer> partdist(String email) throws Exception {
			
		return dashboarddaoqdsl.part(email);
	}


	@Override
	public List<AccuracyResFin> accuracy(String email) throws Exception {
		
		return dashboarddaoqdsl.accuracy(email);
	}


	@Override
	public List<AccuracyTransitionRes> accuracy_transition(String email) throws Exception {
		
		return dashboarddaoqdsl.accuracy_transition(email);
	}


	
	
}
