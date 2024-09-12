package com.Gaowtam.services;

import com.Gaowtam.entities.Contact;
import com.Gaowtam.entities.User;

import java.util.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

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

    // List<Contact>getByUser(User user);
    Page<Contact>getByUser(User user,int page,int size,String sortField,String sortDirection);
}
