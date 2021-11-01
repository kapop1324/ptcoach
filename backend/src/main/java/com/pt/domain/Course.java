package com.pt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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
@Table(name="course")
public class Course {
	
	@Id
	@GeneratedValue
	private int idx;
	
	@Column(name = "course_name")
	private String coursename;
	
	@Column(name = "exercise_name")
	private String exercisename;
	
	@Column(name = "step")
	private int step;
	
	@Column(name = "set")
	private int set;
	
	@Builder
	public Course(String coursename, String exercisename, int step, int set) {

		this.coursename = coursename;
		this.exercisename = exercisename;
		this.step = step;
		this.set = set;
		
	}

}
