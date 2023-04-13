package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Professor;
import diplomas_mgt_app_skeleton.model.Subject;
import diplomas_mgt_app_skeleton.model.Thesis;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ProfessorServiceImpl implements ProfessorService {

    @Override
    public Professor retrieveProfile(String profile) {
        return null;
    }

    @Override
    public void saveProfile(Professor theprofessor) {

    }

    @Override
    public List<Subject> listProfessorSubjects(int pr_id) {
        return null;
    }

    @Override
    public void addSubject(int pr_id, Subject newSubject) {

    }

    @Override
    public List<Application> listApplications(String str, int in) {
        return null;
    }

    @Override
    public List<Thesis> listProfessorTheses(int pr_id) {
        return null;
    }

    @Override
    public void assignSubject(String str, int in) {

    }
}
