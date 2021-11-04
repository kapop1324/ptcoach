package com.pt.model.dao.qdsl;

import com.pt.domain.*;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Repository
public class ExerciseRecordDaoQdsl {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    QExerciseRecord qExerciseRecord = QExerciseRecord.exerciseRecord;
    QCourse qCourse = QCourse.course;
    QExercise qExercise = QExercise.exercise;
    QUser qUser = QUser.user;
    QExerciseImage qExerciseImage = QExerciseImage.exerciseImage;

    //user 따른 기록 조회
    public ExerciseRecord correctionInfo(String useremail){
        ExerciseRecord correctionInfo = jpaQueryFactory
                .select(qExerciseRecord)
                .from(qExerciseRecord)
                .where(qExerciseRecord.useremail.eq(useremail)).fetchOne();

        return correctionInfo;

    }

    //코스운동 운동 설명 페이지
    //코스명을 받으면 -> 운동고유번호, 운동명, 단계, 세트
    // 운동고유번호를 통해 -> exercise_image에서 사진경로,단계,설명 필요
    public List<CourseDetailRes> course_detail(String coursename){
        Stack<CourseDetailRes> stack = new Stack<>();

        //where부분에 ne 부분에 exercise_idx를 찾는 select문이 들어가야함
        //qCourse부분에 exercise_idx가 없기 때문에 받는것도 필요할듯
        List<Tuple> course_list = jpaQueryFactory.select(qCourse,qExercise,qExerciseImage)
                .from(qCourse).innerJoin(qExercise).on(qCourse.exercisename.eq(qExercise.name))
                .innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
                .where(qExerciseImage.step.ne(jpaQueryFactory.select(qCourse.exerciseidx).from(qCourse).where(qCourse.coursename.eq(coursename)))
                        .and(qCourse.coursename.eq(coursename)))
                .orderBy(qCourse.step.desc(),qExerciseImage.step.desc())
                .fetch();

        List<CourseDetailRes> res = new ArrayList<>();

        for(Tuple t : course_list){

            Course c = t.get(0,Course.class);
            Exercise ex = t.get(1,Exercise.class);
            ExerciseImage exi = t.get(2,ExerciseImage.class);
            stack.add(new CourseDetailRes(c,ex,exi));
        }
        while(!stack.isEmpty()){
            res.add(stack.pop());
        }
        return res;


    }


}
