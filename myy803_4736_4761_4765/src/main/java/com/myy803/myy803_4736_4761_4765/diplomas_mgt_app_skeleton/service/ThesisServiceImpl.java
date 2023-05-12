package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ThesisDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Thesis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ThesisServiceImpl implements ThesisService{

    @Autowired
    private final ThesisDAO thesisDAO;

    public ThesisServiceImpl(ThesisDAO thesisDAO) {
        this.thesisDAO = thesisDAO;
    }

    @Override
    public Thesis findById(int th_id) {
        Thesis thesis = thesisDAO.findById(th_id);
        return thesis;
    }

    @Override
    @Transactional
    public void setGrade(int th_ID, float implementationGrade, float presentationGrade, float reportGrade) {
        float finalGrade = ((float) 0.7 * implementationGrade) + ((float) 0.15 * presentationGrade) + ((float)0.15 * reportGrade);
        Thesis assignedThesis = thesisDAO.findById(th_ID);
        assignedThesis.setGrade(finalGrade);

    }

    @Override
    @Transactional
    public List<Thesis> getThesis(List<Subject> mySubjects){
        List<Thesis> allThesis = thesisDAO.findAll();
        List<Thesis> myThesis = new ArrayList<>();
        for (Subject s : mySubjects){
            if (!s.isSub_availability()){
                for (Thesis t : allThesis){
                    if (s.getSub_id() == t.getSubject().getSub_id()){
                        myThesis.add(t);
                    }
                }
            }
        }
        return myThesis;
    }
}
