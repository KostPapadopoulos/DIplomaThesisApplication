package diplomas_mgt_app_skeleton.model;

import lombok.Setter;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;

@Getter
@Setter
@Entity
@Table (name = "THESIS")
public class Thesis {

    @Id
    @Column (name = "th_id")
    private int th_id;

    @Column (name = "subject")
    private Subject subject;

    @Column(name = "student")
    private Student student;
    // TODO prepei na to baloyme kai sthn bash

    public Thesis() {}

    public Thesis(Student student, Subject subject){
        this.student = student;
        this.subject = subject;
    }

    public Thesis(int th_id, Student student, Subject subject) {
        this.th_id = th_id;
        this.student = student;
        this.subject = subject;
    }
}
