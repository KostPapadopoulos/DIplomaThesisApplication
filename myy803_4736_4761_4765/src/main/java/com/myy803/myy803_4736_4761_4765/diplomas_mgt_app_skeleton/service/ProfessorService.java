package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfessorService {

    public Professor retrieveProfile(String userName);

    public void saveProfile(Professor theprofessor);

    public List<Subject> listProfessorSubjects(Professor theProfessor);

    public void addSubject(String userName, Subject newSubject);

    public List<Application> listApplications(String str);

    public List<Thesis> listProfessorThesis(Professor theProfessor);

    public int assignSubject(String str, String in, float th1, int th2);

    public boolean checkForSameTitle(Subject theSubject);

    public boolean checkForSameAssignement(int sub_id);

    public boolean checkForSameName(String userName);
}
