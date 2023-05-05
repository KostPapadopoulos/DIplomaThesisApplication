package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {

    public Student findById(int st_id);

    public List<Subject> listSubjects();

    public Student findByUsername(String username);

    public void saveProfile(Student theStudent);

    public Student retrieveProfile(String username);

    public List<Application> listStudentApplications(Student theStudent);

    public void applyToSubject(String subjectName, Student theStudent);

    public void save(Student theStudent);

    public void deleteById(int theId);
}
