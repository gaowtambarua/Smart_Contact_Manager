package com.Gaowtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Gaowtam.entities.Contact;
import com.Gaowtam.services.ContactService;

@RestController//meane json code return
@RequestMapping("/api")
public class ApiController {
    //get contact

    @Autowired
    private ContactService contactService;

    @GetMapping("/contacts/{contactId}")
    public Contact getContact(@PathVariable String contactId)
    {
        return contactService.getById(contactId);
    }
}
