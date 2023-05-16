package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ThesisDAO extends JpaRepository<Thesis, Integer> {

    public Thesis findById(int th_id);

    public List<Thesis> findAll();
}
