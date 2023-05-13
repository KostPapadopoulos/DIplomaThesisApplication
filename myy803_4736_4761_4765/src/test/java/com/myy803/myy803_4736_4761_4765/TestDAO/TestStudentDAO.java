package com.myy803.myy803_4736_4761_4765.TestDAO;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class TestStudentDAO {

    @Autowired
    StudentDAO studentDAO;

    @Test
    void testStudentDAOJPAIsNotNull(){
        Assertions.assertNotNull(studentDAO);
    }

    @Test
    void testFindByUsernameReturnsStudent(){
        Student storedStudent = studentDAO.findByUsername("Elias");
        Assertions.assertNotNull(storedStudent);
        Assertions.assertEquals("Elias Papathanasiou", storedStudent.getFullName());
    }
}
