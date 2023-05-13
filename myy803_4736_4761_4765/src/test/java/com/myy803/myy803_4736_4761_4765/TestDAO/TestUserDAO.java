package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.UserDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestUserDAO {

    @Autowired
    UserDAO userDAO;

    @Test
    void testUserDAOJPAIsNotNull(){
        Assertions.assertNotNull(userDAO);
    }

    @Test
    void testFindByIdReturnsUser(){
        User storedUser = userDAO.findById(1);
        Assertions.assertNotNull(storedUser);
        Assertions.assertEquals("Kostas", storedUser.getUsername());

    }

    @Test
    void testFindByUsernameReturnsUser(){
        Optional<User> storedUser = userDAO.findByUsername("Thanasis");
        Assertions.assertNotNull(storedUser);
        Assertions.assertEquals("Thanasis", storedUser.get().getUsername());
    }
}
