package com.Gaowtam.services.impl;

import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.Gaowtam.entities.User;
import com.Gaowtam.helpers.AppConstats;
import com.Gaowtam.helpers.ResourceNotFoundException;
import com.Gaowtam.helpers.helper;
import com.Gaowtam.repositories.UserRepo;
import com.Gaowtam.services.EmailService;
import com.Gaowtam.services.UserService;

@Service
// control . auto create function
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) {
        // user id:have to generate
        String userId = UUID.randomUUID().toString();
        user.setUserId(userId);

        // password encode
        // user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // set the user role
        // user.setRoleList(List.of("Admin"));

        user.setRoleList(List.of(AppConstats.ROLE_USER));

        logger.info(user.getProvider().toString());

        String emailToken = UUID.randomUUID().toString();
        user.setEmailToken(emailToken);
        User saveUser = userRepo.save(user);
        String emailLink = helper.getLinkForEmailVerification(emailToken);
        emailService.sendEmail(saveUser.getEmail(), "Verify Account : Email Contact Manager", emailLink);
        return saveUser;
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    // @Override
    // public Optional<User> getUserById(String id) {

    // return userRepo.findById(id);
    // }

    // @Override
    // public Optional<User> updateUser(User user) {
    // User user2=userRepo.findById(user.getUserId()).orElseThrow(()->new
    // ResourceNotFoundException("User Not Found"));
    // //update user2 from user;
    // user2.setName(user.getName());
    // user2.setEmail(user.getEmail());
    // user2.setPassword(user.getPassword());
    // user2.setAbout(user.getAbout());
    // user2.setPhoneNumber(user.getPhoneNumber());
    // user2.setProfilePic(user.getProfilePic());
    // user2.setEnabled(user.isEnabled());
    // user2.setEmmailVerified(user.isEmmailVerified());
    // user2.setPhoneVerified(user.isPhoneVerified());
    // user2.setProviders(user.getProviders());
    // user2.setProvidersUserId(user.getProvidersUserId());

    // //save the user in database;

    // User save=userRepo.save(user2);
    // return Optional.ofNullable(save);
    // }

    // @Override
    // public void deleteUser(String id) {

    // User user2=userRepo.findById(id).orElseThrow(()->new
    // ResourceNotFoundException("User Not Found"));
    // userRepo.delete(user2);
    // }

    // @Override
    // public boolean isUserExist(String userId) {
    // User user2=userRepo.findById(userId).orElse(null);
    // return user2!=null?true:false;
    // }

    // @Override
    // public boolean isUserExitByEmail(String email) {
    // User user=userRepo.findByEmail(email).orElse(null);
    // return user!=null?true:false;
    // }

    // @Override
    // public List<User> getAlUsers() {
    // return userRepo.findAll();
    // }

}
