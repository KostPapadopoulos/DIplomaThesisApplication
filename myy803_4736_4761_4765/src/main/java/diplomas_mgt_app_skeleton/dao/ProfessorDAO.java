package diplomas_mgt_app_skeleton.dao;

import diplomas_mgt_app_skeleton.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfessorDAO extends JpaRepository<Professor, Integer> {

    public Professor findById(int pr_id);

}
