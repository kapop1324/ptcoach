package com.pt.model.service;

import java.util.List;

import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.CourseResFin;

public interface CourseService {
	
	public List<CourseResFin> course_list() throws Exception;
	
	public List<CourseDetailRes> course_detail(String coursename) throws Exception;

}
