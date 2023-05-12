package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;

import java.util.List;

public interface ApplicationService {

    public List<Application> getSubApplications(int sub_id);
}
