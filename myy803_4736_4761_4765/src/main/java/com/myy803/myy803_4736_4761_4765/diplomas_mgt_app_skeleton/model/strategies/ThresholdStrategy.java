package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ThresholdStrategy implements BestApplicantStrategy{

    private final int thresholdGrade;

    private final int thresholdCourses;

    public ThresholdStrategy(int th1, int th2){
        this.thresholdGrade = th1;
        this.thresholdCourses = th2;
    }

    @Override
    public Student findBestApplicant(List<Application> application) {
        List<Student> tempList = new ArrayList<>();
        for (int i = 0; i < application.size(); i++){
            if (application.get(i).getStudent().getCurrentAvgGrade() > thresholdGrade && application.get(i).getStudent().getNumberOfRemCourses() < thresholdCourses){
                tempList.add(application.get(i).getStudent());
            }
        }
        Random rand = new Random();
        Student resultStudent = tempList.get(rand.nextInt(tempList.size()));
        return resultStudent;
    }
}
