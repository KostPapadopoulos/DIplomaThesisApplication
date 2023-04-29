package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public abstract class TemplateStrategyAlgorithm implements BestApplicantStrategy {

    public TemplateStrategyAlgorithm() {}

    public Student findBestApplicant(List<Application> application) {
        Application j = application.get(0);
        for (int i =1; i< application.size(); i++){
            int temp = compareApplications(application.get(i),j);
            if (temp == application.get(i).getStudent().getSt_id()) {
                j = application.get(i);
            }
        }
        return j.getStudent();
    }
    public abstract int compareApplications(Application app1, Application app2);
}
