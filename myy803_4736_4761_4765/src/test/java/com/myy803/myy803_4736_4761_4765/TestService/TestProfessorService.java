package com.myy803.myy803_4736_4761_4765.TestService;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.*;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ProfessorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestProfessorService {

    @Autowired
    ProfessorDAO professorDAO;

    @Autowired
    StudentDAO studentDAO;

    @Autowired
    ThesisDAO thesisDAO;

    @Autowired
    SubjectDAO subjectDAO;

    @Autowired
    ApplicationDAO applicationDAO;

    @Autowired
    ProfessorService professorService;

    @Test
    void testProfessorServiceIsNotNull(){
        Assertions.assertNotNull(professorService);
    }

    @Test
    public void testListProfessorSubjects(){
        Professor professor = professorDAO.findByUsername("Vasileiadis");
        List<Subject> subjectList = professorService.listProfessorSubjects(professor);
        Assertions.assertNotNull(subjectList);
        for (Subject s : subjectList){
            Assertions.assertEquals("Vasileiadis", s.getProfessor().getUsername());
        }
    }

    @Test
    public void testListProfessorThesis(){
        Professor professor = professorDAO.findByUsername("Vasileiadis");
        List<Thesis> subjectList = professorService.listProfessorThesis(professor);
        Assertions.assertNotNull(subjectList);
        for (Thesis t : subjectList){
            Assertions.assertEquals("Vasileiadis", t.getSubject().getProfessor().getUsername());
        }
    }

    /*
    @Test
    public void testAssignSubject(){
        Student kostas = studentDAO.findByUsername("Kostas");
        Student elias = studentDAO.findByUsername("Elias");
        Student thanasis = studentDAO.findByUsername("Thanasis");
        Student giorgos = studentDAO.findByUsername("Giorgos");
        professorService.assignSubject("Databases 2 ", "BestAverageGrade", -1, -1);
        Thesis thesis = thesisDAO.findById(1);
        Assertions.assertEquals("Thanasis", thesis.getStudent().getUsername());

        professorService.assignSubject("Software Engineering", "FewestCoursesStrategy", -1,-1);
        Thesis thesis2 = thesisDAO.findById(2);
        Assertions.assertEquals("Kostas", thesis2.getStudent().getUsername());

        professorService.assignSubject("Software Development I", "ThresholdStrategy", (float)6.5, 12);
        Thesis thesis3 = thesisDAO.findById(3);
        Assertions.assertEquals("Elias", thesis3.getStudent().getUsername());

        Professor vasileiadis = professorDAO.findByUsername("Vasileiadis");
        Subject subjectForRandom = new Subject("Random Subject2", "Objectives", true, vasileiadis);
        subjectDAO.save(subjectForRandom);
        Application randomApplication = new Application(subjectForRandom, kostas);
        applicationDAO.save(randomApplication);
        professorService.assignSubject("Random Subject2", "RandomStrategy", -1,-1);
        Thesis thesis4 = thesisDAO.findById(4);
        Assertions.assertEquals("Kostas", thesis4.getStudent().getUsername());

    }

     */

    @Test
    public void testCheckForSameTitle(){
        Subject subject = subjectDAO.findByTitle("Databases 2 ");
        boolean result = professorService.checkForSameTitle(subject);
        Assertions.assertEquals(false,result);
    }

    @Test
    public void testCheckForSameAssignement(){
        boolean result = professorService.checkForSameAssignement(1);
        Assertions.assertTrue(result);
    }

    @Test
    public void testCheckForSameName(){
        boolean result = professorService.checkForSameName("Kostas");
        Assertions.assertTrue(result);
    }
}
