package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestSubjectServiceWithMocks {

    @MockBean
    SubjectDAO subjectDAO;

    @Test
    void testFindAll(){
        List<Subject> subjectList = new ArrayList<>();

        when(subjectDAO.findAll()).thenReturn(subjectList);
    }

    @Test
    void testFindById(){
        Professor prof = new Professor();
        Subject subject = new Subject(1,"Title","Title Objective",true,prof);
        when(subjectDAO.findById(subject.getSub_id())).thenReturn(subject);
    }
}
