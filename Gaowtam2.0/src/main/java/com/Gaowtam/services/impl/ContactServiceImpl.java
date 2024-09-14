package com.Gaowtam.services.impl;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.Gaowtam.entities.Contact;
import com.Gaowtam.entities.User;
import com.Gaowtam.helpers.ResourceNotFoundException;
import com.Gaowtam.repositories.ContactRepo;
import com.Gaowtam.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepo contactRepo;

    @Override
    public Contact save(Contact contact) {
        String contactId = UUID.randomUUID().toString();
        contact.setId(contactId);
        return contactRepo.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public List<Contact> getAll() {
        return contactRepo.findAll();
    }

    @Override
    public Contact getById(String id) {
        return contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id" + id));
    }

    @Override
    public void delete(String id) {

        var contact = contactRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact not found with given id" + id));

        contactRepo.delete(contact);
    }

    @Override
    public List<Contact> getByUserId(String userId) {
        return contactRepo.findByUserId(userId);
    }

    // @Override
    // public List<Contact> getByUser(User user) {
    // return contactRepo.findByUser(user);
    // }

    @Override
    public Page<Contact> getByUser(User user, int page, int size, String sortBy, String direction) {

        Sort sort = direction.equals("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUser(user, pageable);
    }

    @Override
    public Page<Contact> searchByName(String nameKeyword, int size, int page, String sortby, String order, User user) {

        Sort sort = order.equals("desc") ? Sort.by(sortby).descending() : Sort.by(sortby).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUserAndNameContaining(user, nameKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByEamil(String emailKeyword, int size, int page, String sortby, String order,
            User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortby).descending() : Sort.by(sortby).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUserAndEmailContaining(user, emailKeyword, pageable);
    }

    @Override
    public Page<Contact> searchByPhone(String phoneKeyword, int size, int page, String sortby, String order,
            User user) {
        Sort sort = order.equals("desc") ? Sort.by(sortby).descending() : Sort.by(sortby).ascending();

        var pageable = PageRequest.of(page, size, sort);

        return contactRepo.findByUserAndPhoneNumberContaining(user, phoneKeyword, pageable);
    }

}
