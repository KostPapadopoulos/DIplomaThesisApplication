package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "APPLICATION")
@Getter
@Setter
public class Application {
    @Id
    @Column (name = "ap_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ap_id;

    @OneToOne
    @JoinColumn(name = "subject_id", referencedColumnName = "sub_id")
    private Subject subject;

    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "st_id")
    private Student student;

    public Application() {}

    public Application(Subject subject, Student student) {
        this.subject = subject;
        this.student = student;
    }
    public Application (int ap_id, Subject subject, Student student){
        this.ap_id = ap_id;
        this.subject = subject;
        this.student = student;
    }
}