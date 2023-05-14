package com.myy803.myy803_4736_4761_4765.TestController;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller.StudentController;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
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

    @Autowired
    StudentService studentService ;


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

    @WithMockUser(value = "zarras")
    @Test
    void testSaveStudentReturnsPage() throws Exception{
        Student student = new Student("vasilis","Vasileios Mpouzampalidis",4, 8,4);

        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", student.getUsername());
        multiValueMap.add("fullName", student.getFullName());
        multiValueMap.add("yearOfStudies", Integer.toString(student.getYearOfStudies()));
        multiValueMap.add("currentAvgGrade", Float.toString(student.getCurrentAvgGrade()));
        multiValueMap.add("numberOfRemCourses", Integer.toString(student.getNumberOfRemCourses()));
        mockMvc.perform(post("/student/save").params(multiValueMap)).andExpect(status().isFound())
                .andExpect(view().name("redirect:/student/main-menu"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListSubjects() throws Exception{
        mockMvc.perform(get("/student/subject-list")).
                andExpect(status().isOk()).
                andExpect(view().name("student/subject-list"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testEditProfile() throws Exception{
        String expectedUsername = "testuser";
        Student expectedStudent = new Student();
        expectedStudent.setUsername(expectedUsername);

        // Mock the behavior of the studentService to return the expected student
        when(studentService.retrieveProfile(expectedUsername)).thenReturn(expectedStudent);

        // Perform the request to the controller
        mockMvc.perform(get("/student/showFormForEdit"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/student-form"))
                .andExpect(model().attribute("username", expectedUsername))
                .andExpect(model().attribute("student", expectedStudent));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testApplyToSubject() throws Exception{
        mockMvc.perform(get("/student/applyToSubject")).
                andExpect(status().isOk()).
                andExpect(view().name("redirect:student/main-menu"));
    }


    @WithMockUser(value = "zarras")
    @Test
    void testViewSubjectDetails() throws Exception{
        mockMvc.perform(get("/student/subjectDetails")).
                andExpect(status().isOk()).
                andExpect(view().name("student/view-details"));
    }
}
