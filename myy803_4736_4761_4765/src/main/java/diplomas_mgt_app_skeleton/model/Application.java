package diplomas_mgt_app_skeleton.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "APPLICATION")
@Getter
@Setter
public class Application {
    @Id
    @Column (name = "ap_id")
    private int ap_id;

    @Column (name = "subject")
    private Subject subject;

    @Column (name = "student")
    private Student student;

    public Application (int ap_id, Subject subject, Student student){
        this.ap_id = ap_id;
        this.subject = subject;
        this.student = student;
    }
}