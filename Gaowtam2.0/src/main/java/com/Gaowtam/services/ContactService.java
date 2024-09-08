package com.Gaowtam.services;

import com.Gaowtam.entities.Contact;

import java.util.*;

public interface ContactService {
    
    //save contacts
    Contact save(Contact contact);

    //update contact
    Contact update(Contact contact);

    //get contacts
    List<Contact>getAll();

    //get contact by id
    Contact getById(String id);

    //delete contact
    void delete(String id);

    //search contact
    List<Contact> search(String name,String email,String phoneNumber);

    //get contacts by userid
    List<Contact>getByUserId(String userId);
}
