package com.myy803.myy803_4736_4761_4765.TestServiceWithMocks;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.ApplicationDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.dao.SubjectDAO;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class TestApplicationServiceWithMocks {

    @MockBean
    private SubjectDAO subjectDAO;

    @MockBean
    private ApplicationDAO applicationDAO;


    @Test
    void testGetSubApplications() {
        int sid = 1;
        Subject s1 = new Subject();
        s1.setSub_id(sid);
        when(subjectDAO.findById(sid)).thenReturn(s1);
        List<Application> subApplications = new ArrayList<>();
        when(applicationDAO.findAll()).thenReturn(subApplications);
    }
}
