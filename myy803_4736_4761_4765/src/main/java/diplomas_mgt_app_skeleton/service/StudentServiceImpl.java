package diplomas_mgt_app_skeleton.service;

import diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import diplomas_mgt_app_skeleton.dao.StudentDAO;
import diplomas_mgt_app_skeleton.dao.SubjectDAO;
import diplomas_mgt_app_skeleton.model.Application;
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentDAO studentRepository;

    @Autowired
    private SubjectDAO subjectDAO;

    @Autowired
    private ApplicationDAO applicationDAO;

    @Autowired
    public StudentServiceImpl(StudentDAO theStudentRepository, SubjectDAO subDAO, ApplicationDAO appDAO) {

        this.studentRepository = theStudentRepository;
        this.subjectDAO = subDAO;
        this.applicationDAO = appDAO;
    }

    public StudentServiceImpl() {super();}


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
    public List<Subject> listSubjects() {
        List<Subject> theList = subjectDAO.findAll();
        return theList;
    }

    @Override
    public Student findByUsername(String username) {
        Student theStudent = studentRepository.findByUsername(username);
        return theStudent;
    }

    @Override
    @Transactional
    public void saveProfile(Student theStudent) {
        studentRepository.save(theStudent);
        
    }


    @Override
    @Transactional
    public Student retrieveProfile(String stdName) {
        Student theStudent = studentRepository.findByUsername(stdName);
        return theStudent;
    }

    @Override
    public List<Application> listStudentApplications(Student theStudent) {
        return theStudent.getApplications();
    }

    @Override
    @Transactional
    public void applyToSubject(String subjectName, Student theStudent) {
        Subject theSubject = subjectDAO.findByTitle(subjectName);
        Application newApplication = new Application(theSubject, theStudent);
        applicationDAO.save(newApplication);
        theStudent.getApplications().add(newApplication);
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
