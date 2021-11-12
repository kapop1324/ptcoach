package com.pt.domain.res;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ExerciseRecordResFin {
    private int idx;

    private List<ExerciseRecordRes> exercise_image;

    @Builder

    public ExerciseRecordResFin(int idx, List<ExerciseRecordRes> exercise_image) {
        this.idx = idx;
        this.exercise_image = exercise_image;
    }
}
