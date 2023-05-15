package com.myy803.myy803_4736_4761_4765.TestController;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller.ProfessorController;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ProfessorService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.SubjectService;
import org.hibernate.mapping.Subclass;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TestProfessorController {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ProfessorController professorController;


    @MockBean
    ProfessorService professorService;

    @BeforeEach
    public void setup(){
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    void testStudentControllerIsNotNull(){
        Assertions.assertNotNull(professorController);
    }

    @Test
    void testMockMvcIsNotNull(){
        Assertions.assertNotNull(mockMvc);
    }

    @WithMockUser(value = "zarras")
    @Test
    void testStudentMainMenuPage() throws Exception{
        mockMvc.perform(get("/professor/main-menu")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/main-menu"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSaveProfessorReturnsPage() throws Exception{
        Professor professor = new Professor("zarras","Apostolos Zarras","Software Development");
        MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
        multiValueMap.add("username", professor.getUsername());
        multiValueMap.add("fullName", professor.getFullName());
        multiValueMap.add("specialty", professor.getSpecialty());
        mockMvc.perform(post("/professor/save").params(multiValueMap)).andExpect(status().isFound())
                .andExpect(view().name("redirect:/professor/main-menu"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListProfessorSubjects() throws Exception {
        mockMvc.perform(get("/professor/subject-list")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/subject-list"));
    }


    @WithMockUser(value = "zarras")
    @Test
    void testListProfessorThesis() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor professor = new Professor("vagelis");
        when(professorService.retrieveProfile(auth.getName())).thenReturn(professor);
        mockMvc.perform(get("/professor/thesis-list")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/thesis-list"));
    }
    @WithMockUser(value = "zarras")
    @Test
    void testListProfessorApplications() throws Exception {
        mockMvc.perform(get("/professor/application-list?subjectId=1")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/application-list"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSelectStrategy() throws Exception {
        mockMvc.perform(get("/professor/select-strategy?subjectId=1")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/select-strategy"));
    }


    @WithMockUser(value = "zarras")
    @Test
    void testDeleteSubject() throws Exception {
        int subjectId = 10;
        mockMvc.perform(post("/professor/delete-subject").param("subjectId", String.valueOf(subjectId))).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/professor/subject-list"));
                //andExpect(status().isOk()).
                //andExpect(view().name("redirect:/professor/subject-list"));
    }

    /*
    @WithMockUser(value = "zarras")
    @Test
    void testThresholdValues() throws Exception {
        int subId = 9;
        String choice = "Test";
        mockMvc.perform(get("/professor/threshold-values")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/threshold-values")).
                andExpect(model().attribute("subjectId", subId)).
                andExpect(model().attribute("choice", choice));
    }


    @WithMockUser(value = "zarras")
    @Test
    void testUpdateSubject() throws Exception {
        Subject subject = subjectService.findById(8);
        mockMvc.perform(get("/professor/save-updated-subject?subjectId=8")).
                andExpect(view().name("redirect:/professor/main-menu")).
                andExpect(model().attribute("subject", subject));
    }
    */
}
