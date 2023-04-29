package diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table (name = "PROFESSOR")
public class Professor {
    @Id
    @Column(name = "pr_id")
    private int pr_id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "specialty")
    private String specialty;

    @Column(name = "password")
    private String password;

    @Column(name = "my_subjects")
    private List<Subject> mySubjects;
    // TODO prepei na to baloyme kai sthn bash

    @Column(name = "my_thesis")
    private List<Thesis> myThesis;
    // TODO prepei na to baloyme kai sthn bash


    public Professor() {}

    public Professor(String fullName, String specialty, String password) {
        this.fullName = fullName;
        this.specialty = specialty;
        this.password = password;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
    public Professor(int pr_id, String fullName, String specialty, String password) {
        this.pr_id = pr_id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.password = password;
        this.mySubjects = new ArrayList<>();
        this.myThesis = new ArrayList<>();
    }
}