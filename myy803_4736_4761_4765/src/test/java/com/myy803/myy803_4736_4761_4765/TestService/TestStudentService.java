package com.myy803.myy803_4736_4761_4765.TestService;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.StudentService;
import org.hibernate.mapping.Subclass;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestStudentService {

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    SubjectDAO subjectDAO;

    @Autowired
    ApplicationDAO applicationDAO;

    @Autowired
    StudentService studentService;

    @Test
    void testStudentServiceIsNotNull(){
        Assertions.assertNotNull(studentDAO);
    }

    @Test
    public void testApplyToSubject(){
        String title = "Databases 2 ";
        Subject subject = subjectDAO.findByTitle(title);
        Assertions.assertNotNull(subject);
        Student student = studentDAO.findByUsername("Thanasis");
        studentService.applyToSubject(subject.getTitle(), student);
        Application theApplication = applicationDAO.findById(11);
        Assertions.assertNotNull(theApplication);
        Assertions.assertEquals(11, theApplication.getAp_id());
    }

    @Test
    public void testCheckForDuplicateApplication(){
        Student student = studentDAO.findByUsername("Thanasis");
        Subject subject = subjectDAO.findByTitle("Software Development II");
        boolean result = studentService.checkForDuplicateApplication(student,subject);
        Assertions.assertEquals(true, result);
    }

}
