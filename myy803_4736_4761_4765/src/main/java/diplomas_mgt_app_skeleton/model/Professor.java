package diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    public Professor(int pr_id, String fullName, String specialty, String password) {
        this.pr_id = pr_id;
        this.fullName = fullName;
        this.specialty = specialty;
        this.password = password;
    }
}