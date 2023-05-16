package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ThesisDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestProfessorServiceWithMocks {
    @MockBean
    ProfessorDAO professorDAO;
    @MockBean
    SubjectDAO subjectDAO;

    @MockBean
    ApplicationDAO applicationDAO;

    @MockBean
    ThesisDAO thesisDAO;

    @Test
    void testRetrieveProfile(){
        String username = "zarras";
        Professor professor = new Professor("zarras");
        when(professorDAO.findByUsername(username)).thenReturn(professor);
    }

    @Test
    void testListProfessorSubjects(){
        List<Subject> subjectList = new ArrayList<>();
        Professor professor = new Professor("zarras");
        Subject subject = new Subject(1,"Test Professor Subjects", "Test Objective", true, professor);
        Subject subject2 = new Subject();
        subject2.setSub_id(2);
        subject2.setSub_availability(true);
        subject2.setTitle("Test 2");
        subjectList.add(subject);
        subjectList.add(subject2);
        when(subjectDAO.findAll()).thenReturn(subjectList);
        Assertions.assertEquals("zarras", subjectList.get(0).getProfessor().getUsername());

    }

    @Test
    void testListProfessorThesis(){
        Student student = new Student("Kostas");
        Professor professor = new Professor("zarras");
        List<Subject> subjectList = new ArrayList<>();
        Subject subject = new Subject(1,"Test Professor Subjects", "Test Objective", true, professor);
        Subject subject2 = new Subject();
        subject2.setSub_id(2);
        subject2.setSub_availability(true);
        subject2.setTitle("Test 2");
        subjectList.add(subject);
        subjectList.add(subject2);
        when(subjectDAO.findAll()).thenReturn(subjectList);

        Thesis thesis = new Thesis(student,subject);
        List<Thesis> allThesis = new ArrayList<>();
        allThesis.add(thesis);
        when(thesisDAO.findAll()).thenReturn(allThesis);
        Assertions.assertEquals(1,allThesis.get(0).getSubject().getSub_id());
    }

    @Test
    void testCheckForSameTitle(){
        Professor prof = new Professor("zarras");
        Subject subject = new Subject(1,"Title test", "Test Objective",true,prof);
        Subject subject2 = new Subject(2,"Title test 2", "Test Objective 2",true,prof);
        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject);
        subjectList.add(subject2);
        boolean res = false;
        when(subjectDAO.findAll()).thenReturn(subjectList);
        Assertions.assertFalse(res);
    }

    @Test
    void testCheckForSameAssignement(){
        int sub_id = 2;
        List<Thesis> theses = new ArrayList<>();
        Professor prof = new Professor("zarras");
        Student student = new Student("Kostas");
        Subject subject = new Subject(2,"Title test", "Test Objective",true,prof);
        Thesis thesis = new Thesis(student,subject);
        theses.add(thesis);
        when(thesisDAO.findAll()).thenReturn(theses);
        Assertions.assertEquals(sub_id,theses.get(0).getSubject().getSub_id());
    }

    @Test
    void testCheckForSameName(){
        String username = "Kostas";
        List<Thesis> theses = new ArrayList<>();
        Professor prof = new Professor("zarras");
        Student student = new Student("Kostas");
        Subject subject = new Subject(2,"Title test", "Test Objective",true,prof);
        Thesis thesis = new Thesis(student,subject);
        theses.add(thesis);
        when(thesisDAO.findAll()).thenReturn(theses);
        Assertions.assertEquals(username,theses.get(0).getStudent().getUsername());
    }

    @Test
    void testAssignSubject(){
        String title = "Title test";
        Professor prof = new Professor("zarras");
        Student student = new Student("Kostas");
        Student student2 = new Student("Giorgos");
        Subject subject = new Subject(2,"Title test", "Test Objective",true,prof);
        when(subjectDAO.findByTitle(title)).thenReturn(subject);
        List<Application> allApplications = new ArrayList<>();
        Application ap1 = new Application(subject,student);
        Application ap2 = new Application(subject,student2);
        Thesis newThesis = new Thesis(student,subject);
        Assertions.assertEquals("Kostas",newThesis.getStudent().getUsername());
    }
}

