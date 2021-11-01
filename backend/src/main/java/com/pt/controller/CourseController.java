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

import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.model.service.CourseService;

@RestController
@RequestMapping("/course")
@CrossOrigin("*")
public class CourseController {
	
	@Autowired
	private CourseService courseservice;
	
	@GetMapping("/list")
	public ResponseEntity<HashMap> course_list(){
		
		HashMap result = new HashMap();
		String message = "";
		
		try {
			
			List<CourseRes> list = courseservice.course_list();
			
			if(list != null) {
				
				message = "success";
				result.put("message", message);
				
				String prev = list.get(0).getCoursename();
				String now = list.get(0).getCoursename();
				
				List<CourseRes> tmp = new ArrayList<CourseRes>();
				List<List<CourseRes>> tmp2 = new ArrayList<List<CourseRes>>();
				
				tmp.add(list.get(0));
			
				for(int i = 1; i < list.size(); i++) {
					now = list.get(i).getCoursename();
					if(!now.equals(prev)) {
						tmp2.add(tmp);
						tmp = new ArrayList<CourseRes>();
						tmp.add(list.get(i));
					}else {
						tmp.add(list.get(i));
					}
					prev = now;
				}
				tmp2.add(tmp);
				
				result.put("courseList", tmp2);
				
			}else {
				
				message = "fail";
				result.put("message", message);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", message);
		}
		
		
		return new ResponseEntity<HashMap>(result,HttpStatus.OK);
	}
	
	@GetMapping("/detail")
	public ResponseEntity<HashMap> course_detail(@RequestParam String coursename){
		
		HashMap result = new HashMap();
		String message = "";
		
		try {
			
			List<CourseDetailRes> list = courseservice.course_detail(coursename);
			
			if(list != null) {
				
				message = "success";
				result.put("message", message);
				
				List<CourseDetailRes> tmp = new ArrayList<CourseDetailRes>();
				List<List<CourseDetailRes>> tmp2 = new ArrayList<List<CourseDetailRes>>();
				String prev = list.get(0).getExercisename();
				String now = list.get(0).getExercisename();
				tmp.add(list.get(0));
			
				for(int i = 1; i < list.size(); i++) {
					now = list.get(i).getExercisename();
					if(!now.equals(prev)) {
						tmp2.add(tmp);
						tmp = new ArrayList<CourseDetailRes>();
						tmp.add(list.get(i));
					}else {
						tmp.add(list.get(i));
					}
					prev = now;
				}
				tmp2.add(tmp);
				
				result.put("courseList", tmp2);
				
				
			}else {
				
				message = "fail";
				result.put("message", message);
				
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("message", message);
		}
		
		
		return new ResponseEntity<HashMap>(result,HttpStatus.OK);
	}
	

}
