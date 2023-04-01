package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public interface BestApplicantStrategy {
    public Student findBestApplicant(List<Application> application);
}
