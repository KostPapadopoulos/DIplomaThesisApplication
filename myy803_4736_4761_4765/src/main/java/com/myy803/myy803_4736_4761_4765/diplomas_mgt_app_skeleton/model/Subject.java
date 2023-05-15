package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table (name = "SUBJECT")
public class Subject {
    @Id
    @Column (name = "sub_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sub_id;

    @Column (name = "title")
    private String title;

    @Column (name = "objective")
    private String objectives;

    @Column(name = "sub_availability")
    private boolean sub_availability;

    @ManyToOne
    @JoinColumn(name = "pr_username", referencedColumnName = "username")
    private Professor professor;

    @Transient
    private ArrayList<Application> applicationList;

    public Subject() {}


    public Subject(String title, String objectives, boolean sub_availability, Professor professor){
        this.title = title;
        this.objectives = objectives;
        this.sub_availability = sub_availability;
        this.professor = professor;
        this.applicationList = new ArrayList<>();
    }

    public Subject (int sub_id, String title, String objectives, boolean sub_availability, Professor professor){
        this.sub_id = sub_id;
        this.title = title;
        this.objectives = objectives;
        this.sub_availability = sub_availability;
        this.professor = professor;
        this.applicationList = new ArrayList<>();
    }
}
