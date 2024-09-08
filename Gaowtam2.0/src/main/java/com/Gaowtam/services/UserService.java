package com.Gaowtam.services;

// import java.util.List;
import java.util.Optional;

import com.Gaowtam.entities.User;

public interface UserService {
    //method
    User saveUser(User user);
    // Optional<User> getUserById(String id);
    // Optional<User> updateUser(User user);
    // void deleteUser(String id);
    // boolean isUserExist(String userId);
    //boolean isUserExitByEmail(String email);//UserName
    // List<User>getAlUsers();

    //add more methods here related user service[logic]

    User getUserByEmail(String email);
}
