package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ThesisDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Thesis;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestThesisDAO {
    @Autowired
    ThesisDAO thesisDAO;

    @Test
    void testThesisDAOJPAIsNotNull(){
        Assertions.assertNotNull(thesisDAO);
    }

    @Test
    void testFindByIdReturnsThesis(){
        Thesis thesis = thesisDAO.findById(1);
        Assertions.assertNotNull(thesis);
        Assertions.assertEquals("Thanasis", thesis.getStudent().getUsername());
    }

}
