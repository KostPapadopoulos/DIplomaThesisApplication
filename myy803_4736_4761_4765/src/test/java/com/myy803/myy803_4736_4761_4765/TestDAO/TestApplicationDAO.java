package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestApplicationDAO {

    @Autowired
    ApplicationDAO applicationDAO;

    @Test
    void testProfessorDAOJPAIsNotNull(){
        Assertions.assertNotNull(applicationDAO);
    }

    @Test
    void testFindByUsernameReturnsProfessor(){
        Application storedApplication = applicationDAO.findById(1);
        Assertions.assertNotNull(storedApplication);
        Assertions.assertEquals("Kostas", storedApplication.getStudent().getUsername());
    }

}
