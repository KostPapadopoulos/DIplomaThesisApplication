package diplomas_mgt_app_skeleton.dao;

import diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

    public Subject findById(int sub_id);
}