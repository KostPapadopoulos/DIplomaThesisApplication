package diplomas_mgt_app_skeleton.model.strategies;

import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.lang.invoke.WrongMethodTypeException;

public class BestApplicantStrategyFactory {
    public BestApplicantStrategyFactory() {super();}

    public static BestApplicantStrategy createStrategy(String name, int thresholdGrade, int thresholdCourses) throws IllegalArgumentException {
        if (name.equals("BestAverageGrade")) {
            BestAvgGradeStrategy strategy = new BestAvgGradeStrategy();
            return strategy;
        }
        else if (name.equals("LeastRemainingCourses")){
            FewestCoursesStrategy strategy = new FewestCoursesStrategy();
            return strategy;
        }
        else if (name.equals("Threshold Strategy")){
            ThresholdStrategy strategy = new ThresholdStrategy(thresholdGrade, thresholdCourses);
            return strategy;
        }
        else if (name.equals("RandomStrategy")){
            RandomStrategy strategy = new RandomStrategy();
            return strategy;
        }
        else {
            throw new IllegalArgumentException("Invalid Strategy Name: " + name);
        }
    }
}
