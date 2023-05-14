package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;


@Getter
@Setter
@Entity
@Table (name = "STUDENT")
public class Student {

    @Id
    @Column(name = "username")
    private String username;

    @Column (name = "full_name")
    private String fullName;

    @Column (name = "year_of_studies")
    private int yearOfStudies;

    @Column (name = "current_avg_grade")
    private float currentAvgGrade;

    @Column(name = "number_of_courses")
    private int numberOfRemCourses;

    @Transient
    private ArrayList<Application> applications;

    public Student() {}

    public Student(String username){
        this.username = username;
    }

    public Student(String fullName,int yearOfStudies, float currentAvgGrade, int numberOfRemCourses){
        this.fullName = fullName;
        this.yearOfStudies = yearOfStudies;
        this.currentAvgGrade = currentAvgGrade;
        this.numberOfRemCourses = numberOfRemCourses;
        this.applications = new ArrayList<>();
    }
    public Student(String username, String fullName,int yearOfStudies, float currentAvgGrade, int numberOfRemCourses) {
        this.username = username;
        this.fullName = fullName;
        this.yearOfStudies = yearOfStudies;
        this.currentAvgGrade = currentAvgGrade;
        this.numberOfRemCourses = numberOfRemCourses;
        this.applications = new ArrayList<>();
    }
}
