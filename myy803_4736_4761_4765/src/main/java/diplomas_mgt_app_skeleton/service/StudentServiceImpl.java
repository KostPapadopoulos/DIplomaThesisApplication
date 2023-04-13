package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.dao.StudentDAO;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentRepository;

    @Autowired
    public StudentServiceImpl(StudentDAO theStudentRepository) {
        this.studentRepository = theStudentRepository;
    }

    public StudentServiceImpl() {}


    @Override
    @Transactional
    public List<Student> findAll() {
        return studentRepository.findAll();
    }

    @Override
    @Transactional
    public Student findById(int st_id) {
        Student result = studentRepository.findById(st_id);

        if (result != null){
            return result;
        }
        else {
            throw new RuntimeException("Did not find student with id: " + st_id);
        }
    }

    @Override
    public void saveProfile(Student theStudent) {
        
    }

    @Override
    public Student retrieveProfile(int st_id) {
        return null;
    }

    @Override
    public List<Subject> listStudentSubjects() {
        return null;
    }

    @Override
    public void applyToSubject(String str, int in) {

    }

    @Override
    @Transactional
    public void save(Student theStudent) {
        studentRepository.save(theStudent);
    }

    @Override
    @Transactional
    public void deleteById(int theId) {
        studentRepository.deleteById(theId);
    }
}
