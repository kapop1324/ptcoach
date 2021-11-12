package com.pt.domain;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    private String email;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "height")
    private int height;
    
    @Column(name = "weight")
    private int weight;
    
    @Column(name = "gender")
    private String gender;
    
    @Column(name = "time")
    private Date time;
    
    @Builder
    public User(String email, String name, int age, int height, int weight, String gender, Date time) {
    	
    	this.email = email;
    	this.name = name;
    	this.age = age;
    	this.height = height;
    	this.weight = weight;
    	this.gender = gender;
    	this.time = time;
    	
    	
    }
    

   
    
}
