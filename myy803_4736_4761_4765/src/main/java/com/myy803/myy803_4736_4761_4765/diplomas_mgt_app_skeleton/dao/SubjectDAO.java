package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectDAO extends JpaRepository<Subject, Integer> {

    public Subject findById(int sub_id);

    public Subject findByTitle(String title);


}
