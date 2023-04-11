package diplomas_mgt_app_skeleton.controller;

import
import diplomas_mgt_app_skeleton.model.Student;
import diplomas_mgt_app_skeleton.model.Subject;
import diplomas_mgt_app_skeleton.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    public StudentController(StudentService thestudentService) {
        this.studentService = thestudentService;
    }
}
