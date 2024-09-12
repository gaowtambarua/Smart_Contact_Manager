package com.Gaowtam.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.Gaowtam.entities.Contact;
import com.Gaowtam.entities.User;

import java.util.List;


@Repository
public interface ContactRepo extends JpaRepository<Contact,String>{
    
    //find the contact by user
    //custom finder method
    // List<Contact> findByUser(User user);
    Page<Contact> findByUser(User user,Pageable pageable);

    //custom query method get all contacts of a user
    @Query("Select c From Contact c WHERE c.user.id= :userid")
    List<Contact> findByUserId(@Param("userid") String userid);
}
