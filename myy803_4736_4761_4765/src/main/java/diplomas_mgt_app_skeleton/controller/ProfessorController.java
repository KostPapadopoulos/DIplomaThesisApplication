package diplomas_mgt_app_skeleton.controller;

import diplomas_mgt_app_skeleton.dao.SubjectDAO;
import diplomas_mgt_app_skeleton.model.*;
import diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategy;
import diplomas_mgt_app_skeleton.model.strategies.BestApplicantStrategyFactory;
import diplomas_mgt_app_skeleton.service.ProfessorService;
import diplomas_mgt_app_skeleton.service.StudentService;
import diplomas_mgt_app_skeleton.service.SubjectService;
import diplomas_mgt_app_skeleton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.security.Security;
import java.util.List;

@Controller
@RequestMapping("/professor")
public class ProfessorController {

    @Autowired
    private final ProfessorService professorService;

    @Autowired
    private final UserService userService;


    @Autowired
    private final SubjectService subjectService;

    public ProfessorController(ProfessorService thePService, UserService theUService, SubjectService theSuService){
        this.professorService = thePService;
        this.userService = theUService;
        this.subjectService = theSuService;
    }


    @RequestMapping("/professor/main-menu")
    public String getProfessorMainMenu(){
        return "professor/main-menu";
    }

    @RequestMapping("professor/profile")
    public String retreiveProfile(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String profName = auth.getName();
        Professor theProfessor = professorService.retrieveProfile(profName);
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
        String stdName = auth.getName();
        Professor theProfessor = professorService.retrieveProfile(stdName);
        theModel.addAttribute("professor", theProfessor);

        return "student/professor-form";
    }
    @RequestMapping("/list-subject")
    public String listProfessorSubjects(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String profName = auth.getName();
        Professor theProfessor = professorService.retrieveProfile(profName);
        List<Subject> mySubjects = theProfessor.getMySubjects();
        theModel.addAttribute("subjects", mySubjects);

        return "professor/subject-list";
    }

    @RequestMapping("/list-thesis")
    public String listProfessorThesis(Model theModel){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String profName = auth.getName();
        Professor theProfessor = professorService.retrieveProfile(profName);
        List<Thesis> myThesis = theProfessor.getMyThesis();
        theModel.addAttribute("thesis", myThesis);

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
