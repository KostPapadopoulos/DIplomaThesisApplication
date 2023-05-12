package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.*;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ApplicationService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.SubjectService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ProfessorService;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private final ProfessorService professorService;


    @Autowired
    private final SubjectService subjectService;

    @Autowired
    private final ThesisService thesisService;

    @Autowired
    private final ApplicationService applicationService;

    public ProfessorController(ProfessorService thePService, SubjectService theSuService, ThesisService theService, ApplicationService appService, ApplicationService applicationService){
        this.professorService = thePService;
        this.subjectService = theSuService;
        this.thesisService = theService;
        this.applicationService = applicationService;
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
    @RequestMapping("/subject-list")
    public String listProfessorSubjects(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        List<Subject> selectedSubjects = professorService.listProfessorSubjects(theProfessor);
        theModel.addAttribute("subjects", selectedSubjects);
        return "professor/subject-list";
    }

    @RequestMapping("/thesis-list")
    public String listProfessorThesis(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        List<Thesis> myThesis = professorService.listProfessorThesis(theProfessor);
        theModel.addAttribute("thesis", myThesis);
        return "professor/thesis-list";
    }

    @RequestMapping("/showFormForAdd")
    public String showSubjectForm(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Professor theProfessor = professorService.retrieveProfile(auth.getName());
        theModel.addAttribute("professor", theProfessor);
        Subject newSubject = new Subject();
        theModel.addAttribute("subject", newSubject);
        return "professor/subject-add-form";
    }

    @RequestMapping("/showFormForUpdateSub")
    public String showSubjectUpdateForm(@RequestParam("subjectId") int theId,
                                        Model theModel){
        Subject theSubject = subjectService.findById(theId);
        theModel.addAttribute("subject", theSubject);
        return "professor/subject-update-form";
    }
    @RequestMapping("/save-subject")
    public String addSubject(@ModelAttribute("subject") Subject theSubject, Model theModel){
        System.out.println(theSubject.getTitle());
        subjectService.save(theSubject);
        return "redirect:/professor/main-menu";
    }

    @RequestMapping("/application-list")
    public String listApplications(@RequestParam("subjectId")int subId, Model theModel){
        Subject theSubject = subjectService.findById(subId);
        List<Application> subApplications = applicationService.getSubApplications(theSubject.getSub_id());
        String choice = "";
        theModel.addAttribute("applicationlist", subApplications);
        theModel.addAttribute("subjectId", subId);
        theModel.addAttribute("choice", choice);
        return "professor/application-list";
    }

    @RequestMapping("/select-strategy")
    public String selectStrategy(@RequestParam("subjectId") int sub_ID, Model theModel){
        Subject theSubject = subjectService.findById(sub_ID);
        theModel.addAttribute("subject", theSubject);
        return "/professor/select-strategy";
    }

    @RequestMapping("/assign")
    public String assignSubject(@RequestParam("subjectId")int subId,@RequestParam(value = "choice")String choice,
                                @RequestParam(value = "thresholdGrade",required = false, defaultValue = "-1") String thresholdGrade,
                                @RequestParam(value = "thresholdCourses", required = false, defaultValue = "-1") String thresholdCourses){
        Subject theSubject = subjectService.findById(subId);
        int thresholdGradeInt = Integer.parseInt(thresholdGrade);
        int thresholdCoursesInt = Integer.parseInt(thresholdCourses);
        professorService.assignSubject(theSubject.getTitle(),choice,thresholdGradeInt,thresholdCoursesInt);
        return "/professor/subject-list";

        //TODO Epishs den leitourgei opws prepei to threshlod strategy
    }

    @RequestMapping("/delete-subject")
    public String deleteSubject(@RequestParam("subjectId") int theId) {

        subjectService.deleteById(theId);

        return "redirect:/professor/list-subject";

    }

    @RequestMapping("/grade-form")
    public String setGrade(@RequestParam("thesisID") int th_ID, Model theModel){
        theModel.addAttribute("thesisID", th_ID);
        return "professor/grade-form";
    }

    @RequestMapping("/save-grades")
    public String saveGrade(@RequestParam("thesisID") int th_ID,
                           @RequestParam("implementationGrade") float implGrade,
                           @RequestParam("presentationGrade") float presGrade,
                           @RequestParam("reportGrade") float reportGrade){

        thesisService.setGrade(th_ID,implGrade,presGrade,reportGrade);
        return "redirect:/professor/main-menu";
    }
}
