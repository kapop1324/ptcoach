package com.pt.controller;

import com.pt.domain.Exercise;
import com.pt.domain.ExerciseRecord;
import com.pt.domain.res.CourseDetailRes;
import com.pt.model.service.ExerciseRecordService;
import io.netty.handler.codec.http.HttpServerKeepAliveHandler;
import org.hibernate.type.LocalDateTimeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/record")
public class ExerciseRecordController {
    public static final Logger logger = LoggerFactory.getLogger(ExerciseRecordController.class);

    @Autowired
    private ExerciseRecordService exerciseRecordService;


    @Autowired
    private HttpServletResponse response;

    @PostMapping("/save")
    public ResponseEntity<HashMap> save(@RequestBody ExerciseRecord exerciseRecord){
        HashMap result = new HashMap();
        String message = "";
        LocalDate aaa = LocalDate.now();

        try{
            ExerciseRecord insert = exerciseRecordService.insert(exerciseRecord.builder()

                    .time(exerciseRecord.getTime())
                    .date(Date.valueOf(aaa))
                    .useremail(exerciseRecord.getUseremail())
                    .courseidx(exerciseRecord.getCourseidx())
                    .accuracy(exerciseRecord.getAccuracy())
                    .build());
            System.out.println(insert);
            if(insert != null){
                message = "success";
                result.put("message", message);
            }else{
                message = "fail";
                result.put("message",message);
            }
        }catch (Exception e){
            e.printStackTrace();
            message = "error";
            result.put("meesage", message);
        }
        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }


    //조회
    @GetMapping("/recordinfo")
    public ResponseEntity<HashMap> recordinfo(@RequestParam String email){
        HashMap result = new HashMap();
        String message = "";

        try{
            ExerciseRecord exerciseRecord = exerciseRecordService.findexerciseRecordEmail(email);
            if(exerciseRecord != null){
                message = "success";
                result.put("recordinfo", exerciseRecord);
            }else{
                message = "fail";

            }
        } catch (Exception e) {
            e.printStackTrace();
            message = "error";
        }
        result.put("message", message);
        return new ResponseEntity<HashMap>(result, HttpStatus.OK);
    }

    @GetMapping("/detail")
    public ResponseEntity<HashMap> course_detail(@RequestParam String coursename) throws Exception{
        HashMap result = new HashMap();
        String message = "";

        try{
            List<CourseDetailRes> list = exerciseRecordService.course_detail(coursename);

            if(list != null){
                message = "success";
                result.put("message", message);

                List<CourseDetailRes> tmp = new ArrayList<>();
                List<List<CourseDetailRes>> tmp2 = new ArrayList<>();
                String prev = list.get(0).getExercisename();
                String now = list.get(0).getExercisename();
                tmp.add(list.get(0));

                for(int i = 1; i< list.size(); i++){
                    now = list.get(i).getExercisename();
                    if(!now.equals(prev)){
                        tmp2.add(tmp);
                        tmp = new ArrayList<CourseDetailRes>();
                        tmp.add(list.get(i));
                    }else{
                        tmp.add(list.get(i));
                    }
                    prev = now;
                }
                tmp2.add(tmp);

                result.put("courseList", tmp2);

            }else{
                message = "fail";
                result.put("message", message);
            }
        }catch (Exception e){
            e.printStackTrace();
            result.put("message", message);
        }

        return new ResponseEntity<HashMap>(result,HttpStatus.OK);
    }
}
