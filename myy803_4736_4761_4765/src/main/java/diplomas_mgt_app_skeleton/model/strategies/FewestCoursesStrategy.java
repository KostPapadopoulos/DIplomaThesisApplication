package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Thesis;

import java.util.ArrayList;
import java.util.Collections;

public class FewestCoursesStrategy extends TemplateStrategyAlgorithm {
    public FewestCoursesStrategy(Thesis thesis){
        Student testStudent;
        ArrayList< Student> test = new ArrayList<Student>();
        test = thesis.getApplicants();
        int min = test.get(0).getNumberOfRemCourses();
        for (int i = 1 ; i < test.size(); i++){
            if (test.get(i).getNumberOfRemCourses() < min){
                min = test.get(i).getNumberOfRemCourses();
            }
        }
        Collections.sort(test, new Student.NumberOfRemCoursesComparator());
        test.get(0)
        // TODO WE HAVE WRITTEN BULLSHIT NEEDS FIXING
    }

    @Override
    public int compareApplications(Application app1, Application app2) {
        app1.getApplications();
    }
}
