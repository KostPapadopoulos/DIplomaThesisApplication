package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.SubjectService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ProfessorService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsChecker;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private final ProfessorService professorService;


    @Autowired
    private final SubjectService subjectService;

    public ProfessorController(ProfessorService thePService, SubjectService theSuService){
        this.professorService = thePService;
        this.subjectService = theSuService;
    }


    @RequestMapping("/main-menu")
    public String getProfessorMainMenu(){
        return "professor/main-menu";
    }

    @RequestMapping("/profile")
    public String retreiveProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        return "professor/profile";
    }

    @RequestMapping("/save")
    public String saveProfile(@ModelAttribute("professor") Professor theProfessor, Model theModel){
        professorService.saveProfile(theProfessor);
        return "redirect:/professor/main-menu";
    }

    @RequestMapping("/showFormForEdit")
    public String editProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        return "professor/professor-form";
    }
    @RequestMapping("/list-subject")
    public String listProfessorSubjects(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        List<Subject> allSubjects = subjectService.findAll();
        for (Subject s : allSubjects){
            if (s.getProfessor().getUsername().equals(theProfessor.getUsername())){
                theProfessor.getMySubjects().add(s);
            }
        }
        List<Subject> mySubjects = theProfessor.getMySubjects();
        theModel.addAttribute("subjects", mySubjects);
        return "professor/subject-list";
    }

    @RequestMapping("/list-thesis")
    public String listProfessorThesis(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        List<Thesis> myThesis = theProfessor.getMyThesis();
        theModel.addAttribute("subjects", myThesis);
        return "professor/subject-list";
    }

    @RequestMapping("/showFormForAdd")
    public String showSubjectForm(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        Subject newSubject = new Subject();

        // TODO H forma den gemizei to pr username gia kapoio logo kai meta den mporei na ginei accessed

        theModel.addAttribute("subject", newSubject);
        return "professor/subject-add-form";
    }

    @RequestMapping("/showFormForUpdateSub")
    public String showSubjectUpdateForm(@RequestParam("subjectId") int theId,
                                        Model theModel){
        List<Subject> theSubjects = (List<Subject>) theModel.getAttribute("subjects");
        theSubjects.size();

        Subject theSubject = subjectService.findById(theId);


        theModel.addAttribute("subject", theSubject);


        return "professor/subject-update-form";
    }
    @RequestMapping("/save-subject")
    public String addSubject(@ModelAttribute("subject") Subject theSubject, Model theModel){
        System.out.println(theSubject.getTitle());
        subjectService.save(theSubject);
        System.out.println("Saved ADERFE ");

        return "redirect:/professor/main-menu";
    }

    @RequestMapping("/applications-list")
    public String listApplications(@RequestParam("subjectid")int subId, Model theModel){
        Subject theSubject = subjectService.findById(subId);
        List<Application> subApplications = theSubject.getApplicationList();
        theModel.addAttribute("applications-list", subApplications);

        return "professor/applications-list";
    }

    @RequestMapping("/assign")
    public String assignSubject(@RequestParam("subjectid")int subId,@RequestParam(value = "choice")String choice,
                                @RequestParam(value = "thresholdGrade",required = false, defaultValue = "-1") String thresholdGrade,
                                @RequestParam(value = "thresholdCourses", required = false, defaultValue = "-1") String thresholdCourses,
                                Model theModel){
        Subject theSubject = subjectService.findById(subId);
        int thresholdGradeInt = Integer.parseInt(thresholdGrade);
        int thresholdCoursesInt = Integer.parseInt(thresholdCourses);
        professorService.assignSubject(theSubject.getTitle(),choice,thresholdGradeInt,thresholdCoursesInt);
        return "/professor/subject-list";
    }

    @RequestMapping("/delete-subject")
    public String deleteSubject(@RequestParam("subjectId") int theId) {

        subjectService.deleteById(theId);

        return "redirect:/professor/subject-list";

    }

}
