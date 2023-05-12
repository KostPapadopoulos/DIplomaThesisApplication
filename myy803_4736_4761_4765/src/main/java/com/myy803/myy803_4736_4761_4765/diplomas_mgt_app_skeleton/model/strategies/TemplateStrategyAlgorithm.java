package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {

    public TemplateStrategyAlgorithm() {}

    public Student findBestApplicant(List<Application> application) {
        Application j = application.get(0);
        for (int i =1; i< application.size(); i++){
            String temp = compareApplications(application.get(i),j);
           if (temp.equals(application.get(i).getStudent().getUsername())) {
               j = application.get(i);
           }
        }
        return j.getStudent();
    }
    public abstract String compareApplications(Application app1, Application app2);
}
