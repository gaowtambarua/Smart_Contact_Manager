package com.Gaowtam.Controllers;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.Gaowtam.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
    private Logger logger=LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    //user add dashboard page
    @RequestMapping("/dashboard")
    public String userDashboard() {
        return "user/dashboard";
    }

    //user add profile page
    // @RequestMapping("/profile")
    // public String userProfile(Principal principal) {

    //     // String name=principal.getName();

    //     String username=helper.getEmailOfLoggedinUser(principal);

    //     logger.info("User Logged in: {}",username);

    //     return "user/profile";
    // }


    @RequestMapping("/profile")
    public String userProfile(Model model,Authentication authentication) {


        return "user/profile";
    }
    
    
    //user add contacts page

    //user view contacts

    //user edit contact

    //user delete contact

    //user search contact
}