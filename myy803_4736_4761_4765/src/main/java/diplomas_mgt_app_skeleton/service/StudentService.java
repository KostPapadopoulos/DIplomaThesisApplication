package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    Student findById(int st_id);

    List<Student> findAll();

    public void saveProfile(Student theStudent);

    public Student retrieveProfile(int st_id);

    List<Application> listStudentApplications(Student theStudent);

    public void applyToSubject(String subjectName, Student theStudent);

    public void save(Student theStudent);

    public void deleteById(int theId);
}
