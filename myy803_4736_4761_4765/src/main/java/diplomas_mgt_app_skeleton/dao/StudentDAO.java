package diplomas_mgt_app_skeleton.dao;


import diplomas_mgt_app_skeleton.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentDAO  extends JpaRepository<Student, Integer> {

    public Student findById(int std_id);

   // Optional<String> findByFullName(String full_name);


}
