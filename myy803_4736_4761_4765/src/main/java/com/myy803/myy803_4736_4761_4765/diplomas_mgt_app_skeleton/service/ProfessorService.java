package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProfessorService {

    public Professor retrieveProfile(String userName);

    public void saveProfile(Professor theprofessor);

    public List<Subject> listProfessorSubjects(String userName);

    public void addSubject(String userName, Subject newSubject);

    public List<Application> listApplications(String str);

    public List<Thesis> listProfessorThesis(String userName);

    public void assignSubject(String str, String in, int th1, int th2);

}
