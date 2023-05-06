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
    public Professor retrieveProfile(User theUser) {
        Professor theProfessor = professorDAO.findByUser(theUser);
        return theProfessor;
    }

    @Override
    @Transactional
    public void saveProfile(Professor theprofessor) {
        professorDAO.save(theprofessor);
    }

    @Override
    @Transactional
    public List<Subject> listProfessorSubjects(User theUser){
        Professor theProfessor = professorDAO.findByUser(theUser);
        return theProfessor.getMySubjects();
    }

    @Override
    @Transactional
    public void addSubject(User theUser, Subject newSubject) {
        Professor theProfessor = professorDAO.findByUser(theUser);
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
    public List<Thesis> listProfessorThesis(User theUser) {
        Professor theProfessor = professorDAO.findByUser(theUser);
        return theProfessor.getMyThesis();
    }

    @Override
    @Transactional
    public void assignSubject(String subjectName, String strategyName, int thresholdG, int thresholdC) {
        Subject theSubject = subjectDAO.findByTitle(subjectName);
        BestApplicantStrategy theStrategy = BestApplicantStrategyFactory.createStrategy(strategyName,thresholdG, thresholdC);
        Student theStudent = theStrategy.findBestApplicant(theSubject.getApplicationList());
        Thesis newThesis = new Thesis(theSubject.getSub_id(),theStudent,theSubject);
        thesisDAO.save(newThesis);
        theSubject.setSub_availability(false);
        subjectDAO.save(theSubject);
    }
}
