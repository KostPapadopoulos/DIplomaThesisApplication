package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ThesisDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Professor;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Thesis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestThesisServiceWithMocks {

    @MockBean
    ThesisDAO thesisDAO;

    @Test
    void testSetGrade(){
        float implementationGrade = 10;
        float presentationGrade = 10;
        float reportGrade = 5;
        int th_ID = 2;
        float finalGrade = ((float) 0.7 * implementationGrade) + ((float) 0.15 * presentationGrade) + ((float)0.15 * reportGrade);

        Thesis thesis = new Thesis();
        thesis.setTh_id(th_ID);
        when(thesisDAO.findById(th_ID)).thenReturn(thesis);

        thesis.setGrade(finalGrade);

        Assertions.assertEquals(9.25,thesis.getGrade());
    }

    @Test
    void testGetThesis(){
        Professor professor = new Professor("zarras");
        Student student = new Student("Kostas");
        Subject subject = new Subject(1,"Test Sub 1","Test Objective Sub 1",true,professor);
        Subject subject2 = new Subject(2,"Test Sub 2","Test Objective Sub 2",true,professor);
        Subject subject3 = new Subject(3,"Test Sub 3","Test Objective Sub 3",true,professor);

        Thesis thesis = new Thesis(1,student,subject,10);
        Thesis thesis2 = new Thesis(2,student,subject2,10);
        Thesis thesis3 = new Thesis(3,student,subject3,10);

        List<Subject> subjectList = new ArrayList<>();
        subjectList.add(subject2);
        subjectList.add(subject3);
        List<Thesis> thesisList = new ArrayList<>();
        thesisList.add(thesis);
        thesisList.add(thesis2);
        thesisList.add(thesis3);

        when(thesisDAO.findAll()).thenReturn(thesisList);

    }
}
