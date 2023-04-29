package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import diplomas_mgt_app_skeleton.model.Thesis;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
