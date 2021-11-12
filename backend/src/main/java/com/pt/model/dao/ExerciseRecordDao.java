package com.pt.model.dao;

import com.pt.domain.ExerciseRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExerciseRecordDao extends JpaRepository<ExerciseRecord,Integer> {

    public ExerciseRecord findByUseremail(String useremail);
}
