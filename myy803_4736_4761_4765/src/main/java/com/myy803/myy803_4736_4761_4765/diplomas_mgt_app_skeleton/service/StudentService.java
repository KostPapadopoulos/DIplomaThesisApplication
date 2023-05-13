package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {

    public List<Subject> listSubjects();

    public Student findByUsername(String userName);

    public void saveProfile(Student theStudent);

    public Student retrieveProfile(String userName);

    public List<Application> listStudentApplications(Student theStudent);

    public void applyToSubject(String subjectName, Student theStudent);

    public void save(Student theStudent);

    public boolean checkForDuplicateApplication(Student theStudent, Subject theSUbject);

}
