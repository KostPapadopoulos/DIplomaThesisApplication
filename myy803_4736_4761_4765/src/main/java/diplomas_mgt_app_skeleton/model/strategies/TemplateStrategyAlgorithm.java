package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {

    public TemplateStrategyAlgorithm() {}

    public Student findBestApplicant() {
        Student student = new Student(1,"Peos",5,7.2F,3,"mple");
        return student;
    }
    public abstract int compareApplications(Application app1, Application app2);
}
