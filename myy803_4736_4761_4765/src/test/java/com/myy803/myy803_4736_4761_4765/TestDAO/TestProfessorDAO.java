package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestProfessorDAO {

    @Autowired
    ProfessorDAO professorDAO;

    @Test
    void testProfessorDAOJPAIsNotNull(){
        Assertions.assertNotNull(professorDAO);
    }

    @Test
    void testFindByUsernameReturnsProfessor(){
        Professor storedProfessor = professorDAO.findByUsername("zarras");
        Assertions.assertNotNull(storedProfessor);
        Assertions.assertEquals("Apostolos Zarras", storedProfessor.getFullName());
    }

}
