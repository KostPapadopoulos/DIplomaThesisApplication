package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService{

    @Autowired
    private SubjectDAO subjectDAO;

    public SubjectServiceImpl() {super();}
    @Override
    @Transactional
    public void save(Subject theSubject) {
        subjectDAO.save(theSubject);
    }

    @Override
    public List<Subject> findAll() {
        List<Subject> theList = subjectDAO.findAll();
        return theList;
    }

    @Override
    public Subject findById(int theId) {
        Subject theSubject = subjectDAO.findById(theId);
        return theSubject;
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        subjectDAO.deleteById(theId);
    }
}
