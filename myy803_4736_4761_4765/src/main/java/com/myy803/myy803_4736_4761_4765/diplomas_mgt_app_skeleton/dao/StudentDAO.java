package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;


import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO  extends JpaRepository<Student, Integer> {

    public Student findByUsername(String userName);

    //public void deleteById(@Param("id") String userName);
}
