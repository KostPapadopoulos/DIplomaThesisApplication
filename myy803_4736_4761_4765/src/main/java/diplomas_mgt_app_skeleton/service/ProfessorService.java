package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.*;

import java.util.List;

public interface ProfessorService {


    public void save(Professor theProfessor);

    public void deleteById(int pr_id);
}
