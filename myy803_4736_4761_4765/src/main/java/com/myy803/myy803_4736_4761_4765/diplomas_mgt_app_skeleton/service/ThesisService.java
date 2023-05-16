package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;


import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Thesis;

import java.util.List;

public interface ThesisService {

    public Thesis findById(int th_id);

    public List<Thesis> findAll();

    public void setGrade(int th_ID, float implementationGrade, float presentationGrade, float reportGrade);

    public List<Thesis> getThesis(List<Subject> mySubjects);
}
