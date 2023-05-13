package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;

import java.util.List;
import java.util.Random;

public class RandomStrategy implements BestApplicantStrategy {
    @Override
    public Student findBestApplicant(List<Application> application) {
        if (application.size() < 2) {
            return application.get(0).getStudent();
        }
        else {
            Random rand = new Random();
            Student result = application.get(rand.nextInt(application.size())).getStudent();
            return result;
        }
    }
}
