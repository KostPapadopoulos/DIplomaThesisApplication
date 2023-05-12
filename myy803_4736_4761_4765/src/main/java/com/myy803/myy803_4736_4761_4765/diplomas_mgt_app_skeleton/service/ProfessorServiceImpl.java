package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ThesisDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategy;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    ProfessorDAO professorDAO;
    @Autowired
    SubjectDAO subjectDAO;

    @Autowired
    ApplicationDAO applicationDAO;

    @Autowired
    ThesisDAO thesisDAO;
    public ProfessorServiceImpl() {
        super();
    }

    @Autowired
    public ProfessorServiceImpl(ProfessorDAO theDAO, SubjectDAO theSDAO, ApplicationDAO apDAO, ThesisDAO thDAO) {
        professorDAO = theDAO;
        subjectDAO = theSDAO;
        applicationDAO = apDAO;
        thesisDAO = thDAO;
    }

    @Override
    @Transactional
    public Professor retrieveProfile(String userName) {
        Professor theProfessor = professorDAO.findByUsername(userName);
        return theProfessor;
    }

    @Override
    @Transactional
    public void saveProfile(Professor theprofessor) {
        professorDAO.save(theprofessor);
    }

    @Override
    @Transactional
    public List<Subject> listProfessorSubjects(Professor theProfessor){
        List<Subject> allSubjects = subjectDAO.findAll();
        List<Subject> selectedSubjects = new ArrayList<>();
        for (Subject s : allSubjects){
            if (s.getProfessor() == null){
                continue;
            }
            else if (s.getProfessor().getUsername().equals(theProfessor.getUsername())){
                selectedSubjects.add(s);
            }
        }
        return selectedSubjects;
    }

    @Override
    @Transactional
    public void addSubject(String userName, Subject newSubject) {
        Professor theProfessor = professorDAO.findByUsername(userName);
        subjectDAO.save(newSubject);
        theProfessor.getMySubjects().add(newSubject);

    }

    @Override
    @Transactional
    public List<Application> listApplications(String name) {
        Subject theSubject = subjectDAO.findByTitle(name);
        return theSubject.getApplicationList();
    }

    @Override
    @Transactional
    public List<Thesis> listProfessorThesis(Professor theProfessor) {
        List<Subject> mySubjects = new ArrayList<>();
        List<Subject> allSubjects = subjectDAO.findAll();
        for (Subject s : allSubjects){
            if (s.getProfessor().getUsername().equals(theProfessor.getUsername())){
                mySubjects.add(s);
            }
        }
        List<Thesis> allThesis = thesisDAO.findAll();
        List<Thesis> myThesis = new ArrayList<>();
        for (Subject s: mySubjects){
            if(!s.isSub_availability()){
                for (Thesis t : allThesis){
                    if (t.getSubject().getSub_id() == s.getSub_id()){
                        myThesis.add(t);
                    }
                }

            }
        }
        return myThesis;
    }

    @Override
    @Transactional
    public void assignSubject(String subjectName, String strategyName, int thresholdG, int thresholdC) {
        Subject theSubject = subjectDAO.findByTitle(subjectName);
        BestApplicantStrategy theStrategy = BestApplicantStrategyFactory.createStrategy(strategyName,thresholdG, thresholdC);
        List<Application> allApplications = applicationDAO.findAll();
        List<Application> subApplications = new ArrayList<>();
        for (Application a : allApplications){
            if (a.getSubject().getSub_id() == theSubject.getSub_id()){
                subApplications.add(a);
            }
        }
        Student theStudent = theStrategy.findBestApplicant(subApplications);
        Thesis newThesis = new Thesis(theStudent,theSubject);
        thesisDAO.save(newThesis);
        theSubject.setSub_availability(false);
        subjectDAO.save(theSubject);
    }
}
