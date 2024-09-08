package com.Gaowtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Gaowtam.entities.User;
import com.Gaowtam.forms.UserForm;
import com.Gaowtam.helpers.Message;
import com.Gaowtam.helpers.MessageType;
import com.Gaowtam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;



@Controller
public class PageControllers {

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index()
    {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String home(Model model)
    {
        model.addAttribute("name", "Welcome To The Home Page");
        model.addAttribute("youtubeChannel", "Jmuna TV");
        model.addAttribute("githubRepo","https://github.com/Gaowtam");
        return "home";
    }

    //about

    @RequestMapping("/about")
    public String aboutPage(Model model)
    {
        model.addAttribute("islogin",false);
        return "about";
    }
    
    //services
    @RequestMapping("/services")
    public String servicesPage()
    {
        return "services";
    }

      //contact
      @GetMapping("/contact")
      public String contactPage() {
          return new String("contact");
      }

      @GetMapping("/login")
      public String loginPage() {
          return new String("login");
      }
      @GetMapping("/signup")
      public String signupPage(Model model) {
        UserForm userForm=new UserForm();

        //default set data
        // userForm.setName("Gaowtam");
        // userForm.setAbout("Thies is about :Write something about yourself");

        model.addAttribute("userForm", userForm);
          return new String("signup");
      }

      //Procesing register
      @RequestMapping(value = "/do-register",method = RequestMethod.POST)
      public String processRegister(@Valid @ModelAttribute UserForm userForm,BindingResult rBindingResult, HttpSession session)
      {
        //fetch form data
        //UserForm
        System.out.println("This is processRegister");
        

        //validate form data

        if(rBindingResult.hasErrors())
        {
            return "signup";
        }

        //save to database

        //userservice

        //UserForm-->User

        // User user=User.builder()
        // .name(userForm.getName())
        // .email(userForm.getEmail())
        // .password(userForm.getPassword())
        // .about(userForm.getAbout())
        // .phoneNumber(userForm.getPhoneNumber())
        // .profilePic("https://www.facebook.com/photo/?fbid=3835683316679175&set=a.1385021651745366")
        // .build();


        User user=new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
        user.setProfilePic("https://www.facebook.com/photo/?fbid=3835683316679175&set=a.1385021651745366");




        User saveUser=userService.saveUser(user);
        System.out.println("user saved  : ");

        //message="Registration Succecssfull"

        //add the message;
        Message m=Message.builder().content("Registration Successfull").type(MessageType.green).build();
        session.setAttribute("message",m); 
        // session.setAttribute("message","Higg"); 

        //redirect to login page;
        return "redirect:/signup";
      }
      
}
