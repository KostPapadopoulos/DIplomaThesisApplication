package diplomas_mgt_app_skeleton.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;


@Getter
@Setter
@Entity
@Table (name = "STUDENT")
public class Student {
    @Id
    @Column (name = "st_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int st_id;
    @Column (name = "email")
    private String email;

    @Column (name = "full_name")
    private String fullName;

    @Column (name = "year_of_studies")
    private int yearOfStudies;

    @Column (name = "current_avg_grade")
    private float currentAvgGrade;

    @Column (name = "password")
    private String password;

    @Column(name = "number_of_courses")
    private int numberOfRemCourses;

    @Column(name = "applications")
    private ArrayList<Application> applications;

    public Student() {}

    public Student(String fullName,int yearOfStudies, float currentAvgGrade, int numberOfRemCourses, String password){
        this.fullName = fullName;
        this.yearOfStudies = yearOfStudies;
        this.currentAvgGrade = currentAvgGrade;
        this.numberOfRemCourses = numberOfRemCourses;
        this.password = password;
        this.applications = new ArrayList<>();
    }
    public Student(int st_id, String fullName,int yearOfStudies, float currentAvgGrade, int numberOfRemCourses, String password) {
        this.st_id = st_id;
        this.fullName = fullName;
        this.yearOfStudies = yearOfStudies;
        this.currentAvgGrade = currentAvgGrade;
        this.numberOfRemCourses = numberOfRemCourses;
        this.password = password;
        this.applications = new ArrayList<>();
    }
}
