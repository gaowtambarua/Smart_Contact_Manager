package com.Gaowtam.Controllers;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.Gaowtam.entities.Contact;
import com.Gaowtam.entities.User;
import com.Gaowtam.forms.ContactForm;
import com.Gaowtam.helpers.Message;
import com.Gaowtam.helpers.MessageType;
import com.Gaowtam.helpers.helper;
import com.Gaowtam.services.ContactService;
import com.Gaowtam.services.ImageService;
import com.Gaowtam.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/user/contacts")
public class ContactController {

    private Logger logger=org.slf4j.LoggerFactory.getLogger(ContactController.class);

    @Autowired
    private ContactService contactService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @RequestMapping("/add")
    //add contact page:handler
    public String addContactView(Model model)
    {
        ContactForm contactForm=new ContactForm();
        // contactForm.setName("Nirob");
        // contactForm.setFavorite(true);
        model.addAttribute("contactForm", contactForm);
        return "user/add_contact";
    }

    //jokhon form action er kaz korbe
    @RequestMapping(value="/add",method = RequestMethod.POST)
    public String saveContact(@Valid @ModelAttribute ContactForm contactForm,BindingResult result, Authentication authentication,HttpSession session)
    {
        //process the form data


        //validate form
        if(result.hasErrors())
        {

            result.getAllErrors().forEach(error->logger.info(error.toString()));

            // session.setAttribute("message","Please correct the following errors");
            session.setAttribute("message",Message.builder()
            .content("Please correct the following errors")
            .type(MessageType.red)
            .build());   
            return "user/add_contact";
        }
        
        String username=helper.getEmailOfLoggedinUser(authentication);
        

        //conver form -->contact
        
        User user=userService.getUserByEmail(username);

        Contact contact=new Contact();


        //process the contact picture

        logger.info("file information : {}",contactForm.getContactImage().getOriginalFilename());


        ///image upload er code

        String fileURL=imageService.uploadImage(contactForm.getContactImage());



        contact.setName(contactForm.getName());
        contact.setFavortie(contactForm.isFavorite());
        contact.setEmail(contactForm.getEmail());
        contact.setPhoneNumber(contactForm.getPhoneNumber());
        contact.setAddress(contactForm.getAddress());
        contact.setDescription(contactForm.getDescription());
        contact.setUser(user);
        contact.setLinkedInLink(contactForm.getLlinedInLink());
        contact.setWebsiteLink(contactForm.getWebsiteLink());
        contact.setPicture(fileURL);

        contactService.save(contact);

        System.out.println(contactForm);


        //set the contact picture url


        //set messsage to  be displayed on  the view
        session.setAttribute("message",
        Message.builder()
            .content("You have succesfully added a new contact")
            .type(MessageType.green)
            .build());

        return "redirect:/user/contacts/add";
    }
}
