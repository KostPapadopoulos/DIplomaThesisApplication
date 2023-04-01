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

    @Column (name = "supervisor")
    private Professor supervisor;

    public Thesis(int th_id, Professor supervisor, Subject subject) {
        this.th_id = th_id;
        this.supervisor = supervisor;
        this.subject = subject;
    }
}
