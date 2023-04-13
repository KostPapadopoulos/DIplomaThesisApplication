package diplomas_mgt_app_skeleton.controller;

import diplomas_mgt_app_skeleton.model.User;
import diplomas_mgt_app_skeleton.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class AuthController {

    public UserService userService;

    public AuthController(UserService theService) {
        this.userService = theService;
    }

    public String login() {
        return "Logged in successfully";
    }

    public String register(Model theModel) {
        return "The registration was successful";
    }

    public String registerUser(User theUser, Model theModel) {
        return "The registration of User: " + theUser.getUsername() + " was successful";
    }
}
