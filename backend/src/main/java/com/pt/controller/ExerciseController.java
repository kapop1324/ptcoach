package com.pt.controller;

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

import com.pt.domain.Exercise;
import com.pt.domain.User;
import com.pt.domain.res.ExerciseRes;
import com.pt.model.service.ExerciseService;

@RestController
@RequestMapping("/exercixe")
@CrossOrigin("*")
public class ExerciseController {
	
		@Autowired
		ExerciseService exerciseservice;
	
	 	@GetMapping("/list")
	    public ResponseEntity<HashMap> exercise_list(){
	    	
	    	HashMap result = new HashMap();
	    	String message = "";
	    	
	    	try {
	    		
	    		List<ExerciseRes> list = exerciseservice.exercise_list();
	    		
	    		if(list != null) {
	    			result.put("exercise_list", list);
	    			message = "success";
	    		}else {
	    			message = "fail";
	    		}
	    		
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "error";
			}
	    	
	    	result.put("message", message);
	    	
	    	return new ResponseEntity<HashMap>(result,HttpStatus.OK);
	    }
	 	
	 	@GetMapping("/detail")
	    public ResponseEntity<HashMap> exercise_detail_list(@RequestParam int idx){
	    	
	    	HashMap result = new HashMap();
	    	String message = "";
	    	
	    	try {
	    		
	    		List<ExerciseRes> list = exerciseservice.exercise_detail_list(idx);
	    		
	    		if(list != null) {
	    			result.put("exercise_list", list);
	    			message = "success";
	    		}else {
	    			message = "fail";
	    		}
	    		
				
			} catch (Exception e) {
				e.printStackTrace();
				message = "error";
			}
	    	
	    	result.put("message", message);
	    	
	    	return new ResponseEntity<HashMap>(result,HttpStatus.OK);
	    }
	 	
}
