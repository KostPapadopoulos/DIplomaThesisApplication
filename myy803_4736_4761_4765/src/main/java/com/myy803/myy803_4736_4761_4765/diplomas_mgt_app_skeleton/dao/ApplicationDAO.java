package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApplicationDAO extends JpaRepository<Application, Integer> {

    public Application findById(int ap_id);
}
