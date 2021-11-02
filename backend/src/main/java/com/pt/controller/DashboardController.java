package com.pt.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pt.domain.res.AccuracyRes;
import com.pt.domain.res.AccuracyResFin;
import com.pt.domain.res.AccuracyTransitionRes;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.PartRes;
import com.pt.model.service.CourseService;
import com.pt.model.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
@CrossOrigin("*")
public class DashboardController {
	
	@Autowired
	private DashboardService dashboardservice;
	
	@GetMapping("/record")
	public ResponseEntity<HashMap> dashboard(@RequestParam String email){
		
		HashMap result = new HashMap();
		String message = "";
		
		try {
			
			//전체 운동시간
			int all_time = dashboardservice.all_time(email);
			result.put("alltime", all_time);
			
			//한달간 운동시간
			int month_time = dashboardservice.month_time(email);
			result.put("monthtime", month_time);
			
			//운동별 정확도
			List<AccuracyResFin> accuracy = dashboardservice.accuracy(email);
			result.put("accuracylist", accuracy);
			
			//운동 분포도
			List<PartRes> partdist = dashboardservice.partdist(email);
			result.put("partdis", partdist);
			
			//운동 정확도 변경추이
			List<AccuracyTransitionRes> accuracy_transition = dashboardservice.accuracy_transition(email);
			result.put("accuracytransition", accuracy_transition);
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", message);
		}
		
		
		return new ResponseEntity<HashMap>(result,HttpStatus.OK);
	}
	
	
	

}
