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

    @RequestMapping("/student/main-menu")
    public String getStudentMainMenu() {
        return "/student/main-menu";
    }

    @RequestMapping("student/profile")
    public String retreiveProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String stdName = auth.getName();
        Student theStudent = studentService.retrieveProfile(stdName);
        theModel.addAttribute("student", theStudent);
        return "student/profile";
    }

    @RequestMapping("/save")
    public String saveProfile(@ModelAttribute("student") Student theStudent, Model theModel){
        studentService.saveProfile(theStudent);
        return "redirect:/student/main-menu";
    }

    @RequestMapping("/list-subject")
    public String listSubjects(Model theModel) {
        List<Subject> allSubjects = subjectService.findAll();
        theModel.addAttribute("subjects", allSubjects);
        return "/student/subject-list";
    }

    @RequestMapping("/applications-list")
    public String listStudentApplications(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String stdName = auth.getName();
        Student theStudent = studentService.retrieveProfile(stdName);
        List<Application> allApplications = studentService.listStudentApplications(theStudent);
        theModel.addAttribute("applications list", allApplications);

        return "/student/applications-list";
    }

    @RequestMapping("/showFormForEdit")
    public String editProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String stdName = auth.getName();
        Student theStudent = studentService.retrieveProfile(stdName);
        theModel.addAttribute("student", theStudent);

        return "student/student-form";
    }

    @RequestMapping("/applyToSubect")
    public String applyToSubject(@RequestParam("subjectid") int sub_id, Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String stdName = auth.getName();
        Student theStudent = studentService.retrieveProfile(stdName);
        Subject theSubject = subjectService.findById(sub_id);
        studentService.applyToSubject(theSubject.getTitle(), theStudent);

        return "student/subject-list";
    }

    @RequestMapping("/subjectDetails")
    public String viewSubjectDetails(@RequestParam("subjectid") int sub_id, Model theModel){
        Subject theSubject = subjectService.findById(sub_id);
        theModel.addAttribute("Subject", theSubject);
        return "student/subject-details";
    }
}
