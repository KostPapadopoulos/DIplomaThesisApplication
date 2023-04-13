package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.*;

import java.util.List;

public interface ProfessorService {

    public Professor retrieveProfile(String profile);

    public void saveProfile(Professor theprofessor);

    public List<Subject> listProfessorSubjects(int pr_id);

    public void addSubject(int pr_id, Subject newSubject);

    public List<Application> listApplications(String str, int in);  // Giati pairnei String kai int ?????

    public List<Thesis> listProfessorTheses(int pr_id);

    public void assignSubject(String str, int in);  // Pali to idio me prin

}
