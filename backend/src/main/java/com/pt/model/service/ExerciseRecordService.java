package com.pt.model.service;

import com.pt.domain.ExerciseRecord;
import com.pt.domain.res.CourseDetailRes;

import java.util.List;

public interface ExerciseRecordService {

    public ExerciseRecord insert(ExerciseRecord exerciseRecord) throws Exception;

    public ExerciseRecord findexerciseRecordEmail(String useremail) throws Exception;

    public List<CourseDetailRes> course_detail(String coursename) throws Exception;
}
