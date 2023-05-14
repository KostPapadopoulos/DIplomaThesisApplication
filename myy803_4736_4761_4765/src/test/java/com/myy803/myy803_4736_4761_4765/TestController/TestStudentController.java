package com.myy803.myy803_4736_4761_4765.TestController;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller.StudentController;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TestStudentController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    StudentController studentController;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testStudentControllerIsNotNull(){
        Assertions.assertNotNull(studentController);
    }

    @Test
    void testMockMvcIsNotNull(){
        Assertions.assertNotNull(mockMvc);
    }

    @WithMockUser(value = "georgia")
    @Test
    void testStudentMainMenuPage() throws Exception{
        mockMvc.perform(get("/student/main-menu")).
                andExpect(status().isOk()).
                andExpect(view().name("student/main-menu"));
    }

    /*
    @WithMockUser(value = "zarras")
    @Test
    void testSaveStudentReturnsPage() throws Exception{
        Student student = new Student("vasilis",4, 8,4);

        MultiValueMap<String, Integer> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("id", student.getUsername());
        multiValueMap.add("yearOfStudies", student.getYearOfStudies());
    }

    */
}
