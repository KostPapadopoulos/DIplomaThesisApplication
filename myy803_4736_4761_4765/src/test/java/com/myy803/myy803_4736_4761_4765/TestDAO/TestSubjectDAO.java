package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestSubjectDAO {

    @Autowired
    SubjectDAO subjectDAO;

    @Autowired
    ProfessorDAO professorDAO;

    @Test
    void testSubjectDAOJPAIsNotNull(){
        Assertions.assertNotNull(subjectDAO);
    }

    @Test
    void testFindByIdReturnsSubject(){
        Subject storedSubect = subjectDAO.findById(1);
        Assertions.assertNotNull(storedSubect);
        Assertions.assertEquals("Databases 2 ", storedSubect.getTitle());
    }

    @Test
    void testFindByTitleReturnsSubject(){
        Subject storedSubect = subjectDAO.findByTitle("Databases 2 ");
        Assertions.assertNotNull(storedSubect);
        Assertions.assertEquals("Vasileiadis", storedSubect.getProfessor().getUsername());
    }


    /*
    This test must comply with the database's foreign key constraints

    @Test
    void testDeleteById(){
        Subject storedSubect = subjectDAO.findById(11);
        Assertions.assertNotNull(storedSubect);
        subjectDAO.deleteById(11);
        Assertions.assertNull(subjectDAO.findById(11));
    }


     */
}
