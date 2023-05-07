package com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.controller;

import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Role;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.Student;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.model.User;
import com.myy803.myy803_4736_4761_4765.diplomas_mgt_app_skeleton.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class AuthController {

    @Autowired
    public UserService userService;

    @RequestMapping("/")
    public String redierctToLogin(){
        return "authentication/signin";
    }

    @RequestMapping("/login")
    public String login() {
        return "authentication/signin";
    }

    @RequestMapping("/register")
    public String register(Model theModel)
    {
        theModel.addAttribute("user", new User());
        return "authentication/signup";
    }

    @RequestMapping("/save")
    public String registerUser(@ModelAttribute("user") User theUser, Model theModel) {
        if (userService.isUserPresent(theUser)){
            theModel.addAttribute("successMessage", "User already registered!");
            return "authentication/signin";
        }

        userService.saveUser(theUser);


        theModel.addAttribute("successMessage", "User registered successfully!");
        return "authentication/signin";
    }
}
