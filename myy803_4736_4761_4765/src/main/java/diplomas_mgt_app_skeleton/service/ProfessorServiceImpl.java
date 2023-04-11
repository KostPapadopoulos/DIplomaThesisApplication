package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Professor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfessorServiceImpl implements ProfessorService {

    @Autowired

    @Override
    public List<Professor> findAll() {

    }

    @Override
    public Professor findById(int pr_id) {
        return null;
    }

    @Override
    public void save(Professor theProfessor) {

    }

    @Override
    public void deleteById(int pr_id) {

    }
}
