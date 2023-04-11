package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();

    public Student findById(int st_id);

    public void save(Student theStudent);

    public void deleteById(int theId);
}
