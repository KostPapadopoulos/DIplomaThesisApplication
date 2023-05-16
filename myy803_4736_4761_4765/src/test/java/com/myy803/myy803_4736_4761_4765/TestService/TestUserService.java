package com.myy803.myy803_4736_4761_4765.TestService;


import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.UserDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Role;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestUserService {

    @Autowired
    UserService userService;
    @Autowired
    UserDAO userDAO;
    @Autowired
    StudentDAO studentDAO;
    @Autowired
    ProfessorDAO professorDAO;

    @Test
    void testUserDAOJpaImplIsNotNull() {
        Assertions.assertNotNull(userService);
    }

    /*

    Those tests must comply with the database foreign key constrains


    @Test
    public void testSaveUserAsStudent() {
        User studentUser = new User();
        studentUser.setUsername("pantelis");
        studentUser.setPassword("pass123");
        studentUser.setRole(Role.STUDENT);
        userService.saveUser(studentUser);

        Optional<User> savedUser = userDAO.findByUsername("pantelis");
        assertTrue(savedUser.isPresent());

        Student savedStudent = studentDAO.findByUsername("pantelis");
        assertEquals("pantelis", savedStudent.getUsername());
    }

    @Test
    public void testSaveUserAsProfessor() {
        User professorUser = new User();
        professorUser.setUsername("giannis");
        professorUser.setPassword("pass123");
        professorUser.setRole(Role.PROFESSOR);
        userService.saveUser(professorUser);

        Optional<User> savedUser = userDAO.findByUsername("giannis");
        assertTrue(savedUser.isPresent());

        Professor savedProfessor = professorDAO.findByUsername("giannis");
        assertEquals("giannis", savedProfessor.getUsername());
    }

     */
}

