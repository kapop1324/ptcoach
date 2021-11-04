package com.pt.model.service;

import com.pt.domain.ExerciseRecord;
import com.pt.domain.res.CourseDetailRes;
import com.pt.model.dao.ExerciseRecordDao;
import com.pt.model.dao.qdsl.ExerciseRecordDaoQdsl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExerciseRecordServiceImpl implements ExerciseRecordService {
    @Autowired
    private ExerciseRecordDao exerciseRecordDao;

    @Autowired
    private ExerciseRecordDaoQdsl exerciseRecordDaoQdsl;


    @Override
    public ExerciseRecord insert(ExerciseRecord exerciseRecord) throws Exception {

        return exerciseRecordDao.save(exerciseRecord);
    }

    @Override
    public ExerciseRecord findexerciseRecordEmail(String useremail) throws Exception {
        return exerciseRecordDaoQdsl.correctionInfo(useremail);
    }

    @Override
    public List<CourseDetailRes> course_detail(String coursename) throws Exception {

        List<CourseDetailRes> list = exerciseRecordDaoQdsl.course_detail(coursename);
        return list;
    }
}
