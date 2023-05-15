package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ProfessorDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.StudentDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.UserDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.UserService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestUserServiceWithMocks {

    @TestConfiguration
    static class UserServiceImplTestContextConfiguration{
        @Bean
        public UserService userService(){
            return new UserServiceImpl();
        }
    }

    @Autowired
    UserService userService = new UserServiceImpl();


    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @MockBean
    UserDAO userDAO;

    @MockBean
    StudentDAO studentDAO;

    @MockBean
    ProfessorDAO professorDAO;

    @Test
    void testSaveUserAsStudent(){
        User user = new User();

        String encodedPassword = "test";
        user.setPassword(encodedPassword);
        when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn(user.getPassword());


    }
}
