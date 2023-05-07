package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "PROFESSOR")
public class Professor {
    @Id
    @Column(name = "username")
    private String username;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "specialty")
    private String specialty;

    @Transient
    private List<Subject> mySubjects;

    @Transient
    private List<Thesis> myThesis;


    public Professor() {}

    public Professor(String username){
        this.username = username;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }

    public Professor(String username, String fullName, String specialty) {
        this.username = username;
        this.fullName = fullName;
        this.specialty = specialty;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
    public Professor( String fullName, String specialty) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
}