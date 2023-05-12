package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "THESIS")
public class Thesis {

    @Id
    @Column (name = "th_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int th_id;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
    private Subject subject;


    @OneToOne
    @JoinColumn(name = "st_username", referencedColumnName = "username")
    private Student student;

    @Column(name = "grade")
    private float grade;

    public Thesis() {}

    public Thesis(Student student, Subject subject){
        this.student = student;
        this.subject = subject;
    }

    public Thesis(int th_id, Student student, Subject subject, float theGrade) {
        this.th_id = th_id;
        this.student = student;
        this.subject = subject;
        this.grade = theGrade;
    }
}
