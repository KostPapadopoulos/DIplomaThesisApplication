package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentRepository;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO theStudentRepository, SubjectDAO subDAO, ApplicationDAO appDAO) {

        this.studentRepository = theStudentRepository;
        this.subjectDAO = subDAO;
        this.applicationDAO = appDAO;
    }

    public StudentServiceImpl() {super();}


    @Override
    @Transactional
    public Student findByUsername(String userName) {
        Student result = studentRepository.findByUsername(userName);

        if (result != null){
            return result;
        }
        else {
            throw new RuntimeException("Did not find student with id: " + userName);
        }
    }

    @Override
    public List<Subject> listSubjects() {
        List<Subject> theList = subjectDAO.findAll();
        return theList;
    }

    @Override
    @Transactional
    public void saveProfile(Student theStudent) {
        studentRepository.save(theStudent);
        
    }


    @Override
    @Transactional
    public Student retrieveProfile(String userName ) {
        Student theStudent = studentRepository.findByUsername(userName);
        return theStudent;
    }

    @Override
    @Transactional
    public void applyToSubject(String subjectName, Student theStudent) {
        Subject theSubject = subjectDAO.findByTitle(subjectName);
        Application newApplication = new Application(theSubject, theStudent);
        applicationDAO.save(newApplication);
    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    public boolean checkForDuplicateApplication(Student theStudent, Subject theSUbject) {
        List<Application> allApplications = applicationDAO.findAll();
        boolean duplicate = false;
        for (Application a : allApplications){
            if (a.getStudent().getUsername().equals(theStudent.getUsername()) && a.getSubject().getSub_id() == theSUbject.getSub_id()){
                duplicate = true;
                break;
            }
        }
        return duplicate;
    }
}
