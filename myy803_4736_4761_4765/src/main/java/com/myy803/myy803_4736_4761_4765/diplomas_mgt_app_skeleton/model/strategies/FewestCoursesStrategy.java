package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;

import java.util.ArrayList;
import java.util.Random;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {

    public FewestCoursesStrategy () {}

    @Override
    public int compareApplications(Application app1, Application app2) {
        Student firstStudent = app1.getStudent();
        Student secondStudent = app2.getStudent();
        if (firstStudent.getNumberOfRemCourses() < secondStudent.getNumberOfRemCourses()){
            return firstStudent.getSt_id();
        }
        else if (firstStudent.getNumberOfRemCourses() > secondStudent.getNumberOfRemCourses()){
            return secondStudent.getSt_id();
        }
        else {
            ArrayList<Integer> tempList = new ArrayList<Integer>();
            tempList.add(firstStudent.getSt_id());
            tempList.add(secondStudent.getSt_id());
            Random random = new Random();
            Integer result = tempList.get(random.nextInt(tempList.size()));
            return result;
        }
    }

}
