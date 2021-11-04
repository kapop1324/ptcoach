package com.pt.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.CourseResFin;
import com.pt.model.dao.CourseDao;
import com.pt.model.dao.qdsl.CourseDaoQdsl;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseDao coursedao;
	
	@Autowired
	private CourseDaoQdsl coursedaoqdsl;

	@Override
	public List<CourseResFin> course_list() throws Exception {
		
		List<CourseResFin> list = coursedaoqdsl.course_list();
		
		return list;
	}

	@Override
	public List<CourseDetailRes> course_detail(String coursename) throws Exception {
		
		List<CourseDetailRes> list = coursedaoqdsl.course_detail(coursename);
		
		return list;
	}
	
}
