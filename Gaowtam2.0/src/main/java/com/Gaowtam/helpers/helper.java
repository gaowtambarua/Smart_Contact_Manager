package com.Gaowtam.helpers;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class helper {
    public static String getEmailOfLoggedinUser(/* Principal principal */Authentication authentication) {
        // Principal principal=(Principal)authentication.getPrincipal();
        if (authentication instanceof OAuth2AuthenticationToken) {
            var aOAuth2AuthenticatinToke = (OAuth2AuthenticationToken) authentication;
            var clientid = aOAuth2AuthenticatinToke.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();

            String username = "";

            if (clientid.equalsIgnoreCase("google")) {
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();
            } else if (clientid.equalsIgnoreCase("github")) {
                System.out.println("Getting email from github");
                username = oauth2User.getAttribute("email") != null ? oauth2User.getAttribute("email").toString()
                        : oauth2User.getAttribute("login").toString() + "@gmail.com";
            }

            // sign with email and password

            // sign with google

            // sign with github
            return username;
        }

        else {
            System.out.println("Getting data from local database");
            return authentication.getName();
        }

    }

    public static String getLinkForEmailVerification(String emailToken) {
        String link = "http://localhost:8082/auth/verify-email?token=" + emailToken;
        return link;
    }
}
