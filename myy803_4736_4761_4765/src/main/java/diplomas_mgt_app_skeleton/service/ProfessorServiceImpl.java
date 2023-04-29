package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import diplomas_mgt_app_skeleton.dao.SubjectDAO;
import diplomas_mgt_app_skeleton.dao.ThesisDAO;
import diplomas_mgt_app_skeleton.model.*;
import diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategy;
import diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategyFactory;
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
    public Professor retrieveProfile(String profile) {
        Professor theProfessor = professorDAO.findByUsername(profile);
        return theProfessor;
    }

    @Override
    @Transactional
    public void saveProfile(Professor theprofessor) {
        professorDAO.save(theprofessor);
    }

    @Override
    @Transactional
    public List<Subject> listProfessorSubjects(String username){
        Professor theProfessor = professorDAO.findByUsername(username);
        return theProfessor.getMySubjects();
    }

    @Override
    @Transactional
    public void addSubject(String profUsername, Subject newSubject) {
        Professor theProfessor = professorDAO.findByUsername(profUsername);
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
    public List<Thesis> listProfessorThesis(String userName) {
        Professor theProfessor = professorDAO.findByUsername(userName);
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
