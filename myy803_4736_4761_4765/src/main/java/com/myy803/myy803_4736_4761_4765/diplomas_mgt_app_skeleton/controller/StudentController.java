package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Application;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Subject;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.SubjectService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private StudentService studentService;

    @Autowired
    public StudentController(StudentService thestudentService, SubjectService theSubjectService) {
        this.studentService = thestudentService;
        this.subjectService = theSubjectService;
    }

    @RequestMapping("/main-menu")
    public String getStudentMainMenu() {
        return "student/main-menu";
    }

    @RequestMapping("/profile")
    public String retreiveProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student theStudent = studentService.retrieveProfile(auth.getName());
        theModel.addAttribute("student", theStudent);
        return "student/profile";
    }

    @RequestMapping("/save")
    public String saveProfile(@ModelAttribute("student") Student theStudent, Model theModel){
        if (theStudent.getCurrentAvgGrade() < 5 || theStudent.getCurrentAvgGrade() > 10){
            String error = "Current Average Grade must be between 5 and 10";
            theModel.addAttribute("errorMessage", error);
            return "student/error";
        }
        else if (theStudent.getNumberOfRemCourses() < 0){
            String error = "Number of Remaining Courses must not be negative";
            theModel.addAttribute("errorMessage", error);
            return "student/error";
        }
        studentService.saveProfile(theStudent);
        return "redirect:/student/main-menu";
    }

    @RequestMapping("/subject-list")
    public String listSubjects(Model theModel) {
        List<Subject> allSubjects = subjectService.findAll();
        theModel.addAttribute("subjects", allSubjects);
        return "student/subject-list";
    }

    @RequestMapping("/showFormForEdit")
    public String editProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student theStudent = studentService.retrieveProfile(auth.getName());
        theModel.addAttribute("student", theStudent);
        return "student/student-form";
    }

    @RequestMapping("/applyToSubject")
    public String applyToSubject(@RequestParam("subjectid") int sub_id, Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student theStudent = studentService.retrieveProfile(auth.getName());
        Subject theSubject = subjectService.findById(sub_id);
        boolean duplicateApplication = studentService.checkForDuplicateApplication(theStudent,theSubject);
        if (duplicateApplication){
            String error = "You have already applied to this subject!";
            theModel.addAttribute("errorMessage", error);
            return "/student/error";
        }
        else if (!theSubject.isSub_availability()){
            String error = "This subject is not available for application!";
            theModel.addAttribute("errorMessage", error);
            return "/student/error";
        }
        else {
            studentService.applyToSubject(theSubject.getTitle(), theStudent);
            return "redirect:/student/main-menu";
        }
    }

    @RequestMapping("/subjectDetails")
    public String viewSubjectDetails(@RequestParam("subjectid") int sub_id, Model theModel){
        Subject theSubject = subjectService.findById(sub_id);
        theModel.addAttribute("subject", theSubject);
        return "student/view-details";
    }
}
