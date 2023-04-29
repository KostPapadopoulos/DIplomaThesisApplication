package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {
    public BestAvgGradeStrategy() {}

    @Override
    public int compareApplications(Application app1, Application app2){
        Student firstStudent = app1.getStudent();
        Student secondStudent = app2.getStudent();
        if (firstStudent.getCurrentAvgGrade() < secondStudent.getCurrentAvgGrade()){
            return secondStudent.getSt_id();
        }
        else if (firstStudent.getCurrentAvgGrade() > secondStudent.getCurrentAvgGrade()){
            return firstStudent.getSt_id();
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
