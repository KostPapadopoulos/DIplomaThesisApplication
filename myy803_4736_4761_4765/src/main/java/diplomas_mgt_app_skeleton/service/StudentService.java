package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;

import java.util.List;

public interface StudentService {
    public void saveProfile(Student theStudent);

    public Student retrieveProfile(int st_id);

    public List<Subject> listStudentSubjects();

    public void applyToSubject(String str, int in);   // TO idio me ta alla, giati pairnei string kai int??????

    public void save(Student theStudent);

    public void deleteById(int theId);
}
