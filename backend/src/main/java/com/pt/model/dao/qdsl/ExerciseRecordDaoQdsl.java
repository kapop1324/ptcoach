package com.pt.model.dao.qdsl;

import com.pt.domain.*;
import com.pt.domain.res.CourseDetailRes;
import com.pt.domain.res.CourseRes;
import com.pt.domain.res.ExerciseRecordRes;
import com.pt.domain.res.ExerciseRecordResFin;
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
    public List<ExerciseRecordResFin> course_detail(String coursename){
        List<Integer> course_idx_list = jpaQueryFactory.select(qCourse.exerciseidx)
                .from(qCourse)
                .where(qCourse.coursename.eq(coursename))
                .groupBy(qCourse.exerciseidx)
                .orderBy(qCourse.exerciseidx.asc())
                .fetch();

        List<String> course_name_list = jpaQueryFactory.select(qCourse.coursename)
                .from(qCourse)
                .where(qCourse.coursename.eq(coursename))
                .groupBy(qCourse.coursename)
                .orderBy(qCourse.coursename.asc())
                .fetch();


        Stack<ExerciseRecordRes> stack = new Stack<>();

        //where부분에 ne 부분에 exercise_idx를 찾는 select문이 들어가야함
        //qCourse부분에 exercise_idx가 없기 때문에 받는것도 필요할듯
        //하나의큰리스트 course전체를 담는리ㅣ스트
        //course하나를 담는 리스트
        //list안에 리스트를 담아서
        List<Tuple> tuple = jpaQueryFactory.select(qCourse, qExerciseImage)
                .from(qCourse).innerJoin(qExercise).on(qCourse.exercisename.eq(qExercise.name))
                .innerJoin(qExerciseImage).on(qExercise.idx.eq(qExerciseImage.exerciseidx))
                .where(qExerciseImage.exerciseidx.eq(qCourse.exerciseidx), qCourse.coursename.eq(coursename))
                .orderBy(qCourse.step.desc(), qExerciseImage.step.desc())
                .fetch();


        List<ExerciseRecordRes> image_list = new ArrayList<>();

        for(Tuple t : tuple){
            Course c = t.get(0, Course.class);
            ExerciseImage exi = t.get(1,ExerciseImage.class);


            stack.add(new ExerciseRecordRes(c.getCoursename(),exi.getPath(), exi.getStep(),exi.getDesc(),c.getExerciseidx(), c.getExercisename()));
        }
        while(!stack.isEmpty()){
            image_list.add(stack.pop());
        }
        List<ExerciseRecordRes> tmp = new ArrayList<>();
        List<ExerciseRecordResFin> res = new ArrayList<>();


        //todo
        // course_name_list 를 도는게 아님 왜냐면 coursename에 해당하는 것만 원하기 때문에 그렇게 바꿔야하고
        // exercise_idx끼리 묶기*
        for(int i =0; i<course_idx_list.size(); i++){
            tmp = new ArrayList<ExerciseRecordRes>();
            for(int j=0; j<image_list.size(); j++){
                if(course_idx_list.get(i).equals(image_list.get(j).getExercise_idx())){
                    tmp.add(new ExerciseRecordRes(image_list.get(j).getCoursename(),
                            image_list.get(j).getPath(),
                            image_list.get(j).getImage_step(),
                            image_list.get(j).getDesc(),
                            image_list.get(j).getExercise_idx(),
                            image_list.get(j).getExercise_name()
                    ));
                }
            }
            res.add(new ExerciseRecordResFin(i+1,tmp));

        }
        return res;
    }
}
