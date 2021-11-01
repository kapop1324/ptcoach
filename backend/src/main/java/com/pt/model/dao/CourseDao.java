package com.pt.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pt.domain.Course;
import com.pt.domain.Exercise;

public interface CourseDao extends JpaRepository<Course, Integer>{

}
