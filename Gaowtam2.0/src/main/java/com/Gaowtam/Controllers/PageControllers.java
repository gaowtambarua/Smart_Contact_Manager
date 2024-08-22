package com.Gaowtam.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class PageControllers {
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
      public String signupPage() {
          return new String("signup");
      }
      
}
