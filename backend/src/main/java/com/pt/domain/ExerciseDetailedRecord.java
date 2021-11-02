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
@Table(name="exercise_detailed_record")
public class ExerciseDetailedRecord {
	
	@Id
	@GeneratedValue
	private int idx;
	
	@Column(name = "accuracy")
	private int accuracy;
	
	@Column(name = "count")
	private int count;
	
	@Column(name = "step")
	private int step;
	
	@Column(name = "exercise_record_idx")
	private int exerciserecordidx;
	
	@Builder
	public ExerciseDetailedRecord(int accuracy, int count, int step, int exerciserecordidx) {

		this.accuracy = accuracy;
		this.count = count;
		this.step = step;
		this.exerciserecordidx = exerciserecordidx;
		
	}

}
