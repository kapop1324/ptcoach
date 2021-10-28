package com.pt.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name="exercise_image")
public class ExerciseImage {
	
	@Id
	@Column(name="path")
	private String path;
	
	@Column(name="step")
	private int step;
	
	@Column(name="exercise_idx")
	private int exerciseidx;

}
