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


    @RequestMapping("/professor/main-menu")
    public String getProfessorMainMenu(){
        return "professor/main-menu";
    }

    @RequestMapping("professor/profile")
    public String retreiveProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDet = (UserDetails) principal;
            User theUser = (User) userDet;
            Professor theProfessor = professorService.retrieveProfile(theUser);
            theModel.addAttribute("professor", theProfessor);
        }

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
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDet = (UserDetails) principal;
            User theUser = (User) userDet;
            Professor theProfessor = professorService.retrieveProfile(theUser);
            theModel.addAttribute("professor", theProfessor);
        }
        return "student/professor-form";
    }
    @RequestMapping("/list-subject")
    public String listProfessorSubjects(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDet = (UserDetails) principal;
            User theUser = (User) userDet;
            Professor theProfessor = professorService.retrieveProfile(theUser);
            theModel.addAttribute("professor", theProfessor);
            List<Subject> mySubjects = theProfessor.getMySubjects();
            theModel.addAttribute("subjects", mySubjects);
        }

        return "professor/subject-list";
    }

    @RequestMapping("/list-thesis")
    public String listProfessorThesis(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Object principal = auth.getPrincipal();
        if (principal instanceof UserDetails){
            UserDetails userDet = (UserDetails) principal;
            User theUser = (User) userDet;
            Professor theProfessor = professorService.retrieveProfile(theUser);
            theModel.addAttribute("professor", theProfessor);
            List<Thesis> myThesis = theProfessor.getMyThesis();
            theModel.addAttribute("subjects", myThesis);
        }
        return "professor/subject-list";
    }

    @RequestMapping("/showFromForAdd")
    public String showSubjectForm(Model theModel){
        Subject newSubject = new Subject();

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
        subjectService.save(theSubject);
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
