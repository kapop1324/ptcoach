package com.pt.model.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pt.domain.Exercise;

public interface ExerciseDao extends JpaRepository<Exercise, Integer>{

}
