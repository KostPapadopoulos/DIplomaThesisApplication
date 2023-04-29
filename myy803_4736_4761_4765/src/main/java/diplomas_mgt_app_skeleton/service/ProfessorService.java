package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.*;

import java.util.ArrayList;
import java.util.List;

public interface ProfessorService {

    public Professor retrieveProfile(String profile);

    public void saveProfile(Professor theprofessor);

    List<Subject> listProfessorSubjects(String username);

    void addSubject(String profUsername, Subject newSubject);

    public List<Application> listApplications(String str);

    public List<Thesis> listProfessorThesis(String userName);

    public void assignSubject(String str, String in, int th1, int th2);

}
