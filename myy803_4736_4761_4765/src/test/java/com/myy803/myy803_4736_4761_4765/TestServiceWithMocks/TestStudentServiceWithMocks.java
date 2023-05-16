package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestStudentServiceWithMocks {

    @MockBean
    StudentDAO studentDAO;

    @MockBean
    SubjectDAO subjectDAO;

    @MockBean
    ApplicationDAO applicationDAO;

    @Test
    void testFindByUsername(){
        Student student = new Student("Kostas");
        when(studentDAO.findByUsername("Kostas")).thenReturn(student);
    }

    @Test
    void testListSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        when(subjectDAO.findAll()).thenReturn(subjectList);
    }

    @Test
    void testRetrieveProfile(){
        Student student = new Student("Kostas");
        when(studentDAO.findByUsername("Kostas")).thenReturn(student);
    }

    @Test
    void testApplyToSubject(){
        Subject subject = new Subject();
        subject.setTitle("Test Apply");
        subject.setSub_id(1);
        when(subjectDAO.findById(1)).thenReturn(subject);
    }

    @Test
    void testCheckForDuplicateApplication(){
        int sub_id = 1;
        Student student = new Student("Kostas");
        Subject subject = new Subject();
        subject.setSub_id(sub_id);
        List<Application> applications = new ArrayList<>();
        Application application = new Application(subject,student);
        applications.add(application);
        when(applicationDAO.findAll()).thenReturn(applications);

    }
}
