package com.Gaowtam.config;

import java.io.IOException;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.Gaowtam.entities.Providers;
import com.Gaowtam.entities.User;
import com.Gaowtam.helpers.AppConstats;
import com.Gaowtam.repositories.UserRepo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthAuthencationSuccessHandler implements AuthenticationSuccessHandler{


    Logger logger=LoggerFactory.getLogger(OAuthAuthencationSuccessHandler.class);

    @Autowired
    private UserRepo userRepo;

    @Override
    public void onAuthenticationSuccess(
        HttpServletRequest request, 
        HttpServletResponse response,
        Authentication authentication) throws IOException, ServletException {
                logger.info("OAuthAuthencationSuccessHandler");
                // response.sendRedirect("/home");      
                
                
                //idetify the provider
                
                var oauth2AuthenicatinToken =(OAuth2AuthenticationToken)authentication;

                String authorziedClientRegistraionId=oauth2AuthenicatinToken.getAuthorizedClientRegistrationId();

                logger.info(authorziedClientRegistraionId);//google naki github login hocce seti show korbe


                var oauthUser=(DefaultOAuth2User)authentication.getPrincipal();
                oauthUser.getAttributes().forEach((key,value)->{
                    logger.info(key+" : "+value);
                });

                User user=new User();
                user.setUserId(UUID.randomUUID().toString());
                user.setRoleList(List.of(AppConstats.ROLE_USER));
                user.setEmmailVerified(true);
                user.setEnabled(true);
                user.setPassword("dummy");

                if(authorziedClientRegistraionId.equalsIgnoreCase("google"))
                {
                    //google
                    user.setEmail(oauthUser.getAttribute("email").toString());
                    user.setProfilePic(oauthUser.getAttribute("picture").toString());
                    user.setName(oauthUser.getAttribute("name").toString());
                    user.setProvidersUserId(oauthUser.getName());
                    user.setProvider(Providers.GOOGLE);
                    user.setAbout("This account is created using google");
                }
                else if(authorziedClientRegistraionId.equalsIgnoreCase("github"))
                {
                    //github
                    String email=oauthUser.getAttribute("email")!=null?
                    oauthUser.getAttribute("email").toString() : oauthUser.getAttribute("login").toString()+"@gmail.com";

                    String picture=oauthUser.getAttribute("avatar_url").toString();
                    String name=oauthUser.getAttribute("login").toString();
                    String providerUserId=oauthUser.getName();

                    user.setEmail(email);
                    user.setProfilePic(picture);
                    user.setName(name);
                    user.setProvidersUserId(providerUserId);
                    user.setProvider(Providers.GITHUB);
                    user.setAbout("This account is created using github");
                }
                else if(authorziedClientRegistraionId.equalsIgnoreCase("linkedind"))
                {
                    //linkedin
                }
                else
                {
                    logger.info("OAuthAuthenicationSuccessHandler: Unknow provider");
                }

                

             





                /*
                
                DefaultOAuth2User user=(DefaultOAuth2User)authentication.getPrincipal();

                // logger.info(user.getName());
                // user.getAttributes().forEach((key,value) -> {
                //     logger.info("{}=>{}",key,value);
                // });

                // logger.info(user.getAuthorities().toString());



                //data database save


                String email=user.getAttribute("email").toString();
                String name=user.getAttribute("name").toString();
                String picture=user.getAttribute("picture").toString();

                //create user and save in database

                User user1=new User();
                user1.setEmail(email);
                user1.setName(name);
                user1.setProfilePic(picture);
                user1.setPassword("password");
                user1.setUserId(UUID.randomUUID().toString());
                user1.setProvider(Providers.GOOGLE);
                user1.setEnabled(true);

                user1.setEmmailVerified(true);
                user1.setProvidersUserId(user.getName());
                user1.setRoleList(List.of(AppConstats.ROLE_USER));
                user1.setAbout("This account is created using google..");

                User user2=userRepo.findByEmail(email).orElse(null);

                if(user2==null)
                {
                    userRepo.save(user1);
                    logger.info("User saved:"+email);
                }
                    
                


                */


                User user2=userRepo.findByEmail(user.getEmail()).orElse(null);
                if(user2==null)
                {
                    userRepo.save(user);
                }






                new DefaultRedirectStrategy().sendRedirect(request, response,"/user/profile");
    }

}
