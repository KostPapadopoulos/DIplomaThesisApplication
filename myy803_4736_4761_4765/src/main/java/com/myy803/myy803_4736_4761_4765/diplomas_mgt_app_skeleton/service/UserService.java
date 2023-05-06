package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.springframework.stereotype.Service;


public interface UserService {

    public void saveUser(User theUser);

    public boolean isUserPresent(User theUser);

    public User findById(int us_id);

}
