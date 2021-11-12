package com.pt.model.service;

import com.pt.domain.ExerciseRecord;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.ExerciseRecordResFin;

import java.util.List;

public interface ExerciseRecordService {

    public ExerciseRecord insert(ExerciseRecord exerciseRecord) throws Exception;

    public ExerciseRecord findexerciseRecordEmail(String useremail) throws Exception;

    public List<ExerciseRecordResFin> course_detail(String coursename) throws Exception;
}
