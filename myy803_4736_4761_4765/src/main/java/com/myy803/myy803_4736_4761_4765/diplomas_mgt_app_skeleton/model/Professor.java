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
    @Column(name = "pr_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pr_id;

    @OneToOne
    @JoinColumn(name = "us_id")
    private User user;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "specialty")
    private String specialty;


    @Transient
    private List<Subject> mySubjects;

    @Transient
    private List<Thesis> myThesis;


    public Professor() {}

    public Professor(String username, String fullName, String specialty, User theUser) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.user = theUser;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
    public Professor(int pr_id, String username, String fullName, String specialty, User theUser) {
        this.pr_id = pr_id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.user = theUser;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
}