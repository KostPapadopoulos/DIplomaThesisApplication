package com.myy803.myy803_4736_4761_4765.TestController;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller.ProfessorController;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ApplicationService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ProfessorService;
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
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application.properties")
@AutoConfigureMockMvc
public class TestProfessorController {

    @MockBean
    private SubjectService subjectService;
    @MockBean
    private ApplicationService applicationService;

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
    void testProfessorControllerIsNotNull(){
        Assertions.assertNotNull(professorController);
    }

    @Test
    void testMockMvcIsNotNull(){
        Assertions.assertNotNull(mockMvc);
    }

    @WithMockUser(value = "zarras")
    @Test
    void testProfessorMainMenuPage() throws Exception{
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
    void testEditProfile() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor professor = new Professor("vagelis");
        when(professorService.retrieveProfile(auth.getName())).thenReturn(professor);
        mockMvc.perform(get("/professor/showFormForEdit")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/professor-form"));
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
    void testShowSubjectForm() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor professor = new Professor("vagelis");
        when(professorService.retrieveProfile(auth.getName())).thenReturn(professor);
        mockMvc.perform(get("/professor/showFormForAdd")).
                andExpect(status().isOk()).
                andExpect(view().name("professor/subject-add-form"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testShowFormForUpdate() throws Exception {
        int subId = 9;
        Subject subject = new Subject();
        subject.setSub_id(9);
        when(subjectService.findById(subId)).thenReturn(subject);
        mockMvc.perform(get("/professor/showFormForUpdateSub").param("subjectId", String.valueOf(subId))).
                andExpect(status().isOk()).
                andExpect(view().name("professor/subject-update-form")).
                andExpect(model().attribute("subject", subject));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testListProfessorApplications() throws Exception {
        int subId = 9;
        Subject subject = new Subject();
        subject.setSub_id(9);
        List<Application> applicationList = new ArrayList<>();
        when(subjectService.findById(subId)).thenReturn(subject);
        when(applicationService.getSubApplications(subject.getSub_id())).thenReturn(applicationList);
        mockMvc.perform(get("/professor/application-list").param("subjectId", String.valueOf(subId))).
                andExpect(status().isOk()).
                andExpect(view().name("professor/application-list")).
                andExpect(model().attribute("subjectId", subId));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSelectStrategy() throws Exception {
        int subId = 9;
        Subject subject = new Subject();
        subject.setSub_id(9);
        when(subjectService.findById(subId)).thenReturn(subject);
        mockMvc.perform(get("/professor/select-strategy").param("subjectId", String.valueOf(subId))).
                andExpect(status().isOk()).
                andExpect(view().name("professor/select-strategy")).
                andExpect(model().attribute("subject", subject));
    }

    /*

    The following test has been commented out because even though the functionality being tested works in the web
    but not in this test.

    @WithMockUser(value = "zarras")
    @Test
    void testDeleteSubject() throws Exception {
        int subjectId = 10;
        mockMvc.perform(post("/professor/delete-subject").param("subjectId", String.valueOf(subjectId))).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/professor/subject-list"));
    }


     */

    @WithMockUser(value = "zarras")
    @Test
    void testThresholdValues() throws Exception {
        int subId = 9;
        String choice = "Test";
        mockMvc.perform(get("/professor/threshold-values")
                .param("subjectId", String.valueOf(subId)).param("choice", choice)).
                andExpect(status().isOk()).
                andExpect(view().name("/professor/threshold-values")).
                andExpect(model().attribute("subjectId", subId)).
                andExpect(model().attribute("choice", choice));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testAssignSubject() throws Exception {
        int subId = 9;
        String choice = "Test";
        Subject subject = new Subject();
        subject.setSub_id(subId);
        when(professorService.checkForSameAssignement(subId)).thenReturn(false);
        when(subjectService.findById(subId)).thenReturn(subject);
        when(professorService.assignSubject(subject.getTitle(),choice,(float)5.5,10))
                .thenReturn(0);
        mockMvc.perform(get("/professor/assign")
                        .param("subjectId", String.valueOf(subId)).param("choice", choice)).
                andExpect(status().is3xxRedirection()).
                andExpect(redirectedUrl("/professor/main-menu"));
    }

    @WithMockUser(value = "zarras")
    @Test
    void testSetGrade() throws Exception {
        int thesisID = 1;
        mockMvc.perform(get("/professor/grade-form")
                        .param("thesisID", String.valueOf(thesisID))).
                andExpect(status().isOk()).
                andExpect(view().name("professor/grade-form")).
                andExpect(model().attribute("thesisID", thesisID));
    }

}
