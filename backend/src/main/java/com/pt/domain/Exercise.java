package com.pt.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
public class Exercise {
	
	@Id
	@GeneratedValue
	private int idx;
	
	private String name;
	
	private String part;
	
	@Builder
	public Exercise(String name, String part) {
		
		this.name = name;
		this.part = part;
		
	}
	
	

}
