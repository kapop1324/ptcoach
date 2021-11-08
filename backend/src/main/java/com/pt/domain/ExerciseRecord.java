package com.pt.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="exercise_record")
public class ExerciseRecord {
	
	@Id
	@GeneratedValue
	private int idx;
	
	@Column(name = "user_email")
	private String useremail;
	
	@Column(name = "course_idx")
	private int courseidx;
	
	@Column(name = "time")
	private int time;
	
	@Column(name = "date")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	@Column(name = "accuracy")
	private int accuracy;
	
	@Builder
	public ExerciseRecord(String useremail, int courseidx, int time, Date date, int accuracy) {

		this.useremail = useremail;
		this.courseidx = courseidx;
		this.time = time;
		this.date = date;
		this.accuracy = accuracy;
		
	}

}
