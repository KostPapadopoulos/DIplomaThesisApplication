package diplomas_mgt_app_skeleton.model.strategies;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements BestApplicantStrategy {
    @Override
    public Student findBestApplicant(List<Application> application) {
        Random rand = new Random();
        Student result = application.get(rand.nextInt(application.size())).getStudent();
        return result;
    }
}
