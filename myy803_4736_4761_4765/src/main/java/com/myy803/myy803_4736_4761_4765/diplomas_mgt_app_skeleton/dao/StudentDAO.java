package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao;


import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO  extends JpaRepository<Student, Integer> {

    public Student findById(int std_id);

    public Student findByUsername(String userName);
   // Optional<String> findByFullName(String full_name);


}
