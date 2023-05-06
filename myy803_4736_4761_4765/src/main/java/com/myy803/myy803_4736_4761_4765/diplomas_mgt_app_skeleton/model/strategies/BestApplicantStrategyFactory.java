package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies;

public class BestApplicantStrategyFactory {
    public BestApplicantStrategyFactory() {super();}

    public static BestApplicantStrategy createStrategy(String name, int thresholdGrade, int thresholdCourses) throws IllegalArgumentException {
        switch (name) {
            case "BestAverageGrade" -> {
                BestAvgGradeStrategy strategy = new BestAvgGradeStrategy();
                return strategy;
            }
            case "LeastRemainingCourses" -> {
                FewestCoursesStrategy strategy = new FewestCoursesStrategy();
                return strategy;
            }
            case "Threshold Strategy" -> {
                ThresholdStrategy strategy = new ThresholdStrategy(thresholdGrade, thresholdCourses);
                return strategy;
            }
            case "RandomStrategy" -> {
                RandomStrategy strategy = new RandomStrategy();
                return strategy;
            }
            default -> throw new IllegalArgumentException("Invalid Strategy Name: " + name);
        }
    }
}
