package com.Gaowtam.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Gaowtam.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User,String>{//database zukto kora
    //extra methods db related operation
    //custoom query methods
    //custom finder methods

    Optional<User> findByEmail(String email);//select email from user

    // Optional<User> fndByEmailAndPassword(String email,String password);
}
