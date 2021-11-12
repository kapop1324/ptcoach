package com.pt.domain.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ExerciseRecordRes {
    private String coursename;
    private String path;
    private int image_step;
    private String desc;
    private int exercise_idx;
    private String exercise_name;

    @Builder

    public ExerciseRecordRes(String coursename, String path, int image_step, String desc, int exercise_idx, String exercise_name) {
        this.coursename = coursename;
        this.path = path;
        this.image_step = image_step;
        this.desc = desc;
        this.exercise_idx = exercise_idx;
        this.exercise_name = exercise_name;
    }
}
