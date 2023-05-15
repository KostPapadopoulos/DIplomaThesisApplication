package com.myy803.myy803_4736_4761_4765.TestController;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller.StudentController;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.StudentService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
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

    @MockBean
    StudentService studentService ;

    @MockBean
    SubjectService subjectService;

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
   void testViewSubjectDetails() throws Exception{
        int sub_id = 10;
        Subject theSubject = new Subject();
        theSubject.setSub_id(sub_id);
        theSubject.setTitle("Test View Details");
        when(subjectService.findById(sub_id)).thenReturn(theSubject);
        mockMvc.perform(get("/student/subjectDetails").param("subjectid", String.valueOf(sub_id))).
                andExpect(status().isOk()).
                andExpect(view().name("student/view-details")).
                andExpect(model().attribute("subject", theSubject));
    }
    @WithMockUser(value = "zarras")
    @Test
    void testEditProfile() throws Exception{
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student theStudent = new Student();
        theStudent.setUsername("Kostas");
        // Mock the behavior of the studentService to return the expected student
        when(studentService.retrieveProfile(auth.getName())).thenReturn(theStudent);

        // Perform the request to the controller
        mockMvc.perform(get("/student/showFormForEdit"))
                .andExpect(status().isOk())
                .andExpect(view().name("student/student-form"))
                .andExpect(model().attribute("student", theStudent));
    }


    @WithMockUser(value = "zarras")
    @Test
    void testApplyToSubject() throws Exception {
        int subjectId = 9;
        Subject subject = new Subject();
        subject.setSub_id(subjectId);
        subject.setTitle("Test Title");
        subject.setSub_availability(true);
        String username = "test";
        Student student = new Student();
        student.setUsername(username);
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        when(studentService.retrieveProfile(auth.getName())).thenReturn(student);
        when(subjectService.findById(subjectId)).thenReturn(subject);
        when(studentService.checkForDuplicateApplication(student, subject))
                .thenReturn(false);
        mockMvc.perform(post("/student/applyToSubject").param("subjectid", String.valueOf(subjectId)))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/student/main-menu"));
    }

}
