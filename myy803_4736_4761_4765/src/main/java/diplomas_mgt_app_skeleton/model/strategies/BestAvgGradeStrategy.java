package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public class BestAvgGradeStrategy extends TemplateStrategyAlgorithm {
    public BestAvgGradeStrategy() {}

    @Override
    public int compareApplications(Application app1, Application app2) {
        return 0;
    }

    @Override
    public Student findBestApplicant(List<Application> application) {
        return null;
    }

    // TODO Needs fixing
}
