package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserDAO extends JpaRepository<User, Integer>{

    public User findById(int us_id0);

    public Optional<User> findByUsername(String userName);

}
