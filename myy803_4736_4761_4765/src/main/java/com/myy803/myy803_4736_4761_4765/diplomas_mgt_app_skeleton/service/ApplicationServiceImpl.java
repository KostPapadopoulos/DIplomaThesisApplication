package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApplicationServiceImpl implements ApplicationService{

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private ApplicationDAO applicationDAO;

    public ApplicationServiceImpl(SubjectDAO theDAO, ApplicationDAO theADAO){
        this.subjectDAO = theDAO;
        this.applicationDAO = theADAO;
    }
    @Override
    public List<Application> getSubApplications(int sub_id) {
        Subject theSubject = subjectDAO.findById(sub_id);
        List<Application> subApplications = new ArrayList<>();
        List<Application> allApplications = applicationDAO.findAll();
        for (Application a : allApplications){
            if (a.getSubject().getSub_id() == theSubject.getSub_id()){
                subApplications.add(a);
            }
        }
        return subApplications;
    }
}
