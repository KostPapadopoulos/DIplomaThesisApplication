package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;

import java.util.ArrayList;
import java.util.Random;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {
    public BestAvgGradeStrategy() {}

    @Override
    public String compareApplications(Application app1, Application app2){
        Student firstStudent = app1.getStudent();
        Student secondStudent = app2.getStudent();
        if (firstStudent.getCurrentAvgGrade() < secondStudent.getCurrentAvgGrade()){
            return secondStudent.getUsername();
        }
        else if (firstStudent.getCurrentAvgGrade() > secondStudent.getCurrentAvgGrade()){
            return firstStudent.getUsername();
        }
        else {
            ArrayList<String> tempList = new ArrayList<String>();
            tempList.add(firstStudent.getUsername());
            tempList.add(secondStudent.getUsername());
            Random random = new Random();
            String result = tempList.get(random.nextInt(tempList.size()));
            return result;

        }
    }

}
