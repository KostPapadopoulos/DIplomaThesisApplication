package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

public interface SubjectService {

    public void save(Subject theSubject);

    public List<Subject> findAll();

    public Subject findById(int theId);

    public void deleteById(int theId);
}
