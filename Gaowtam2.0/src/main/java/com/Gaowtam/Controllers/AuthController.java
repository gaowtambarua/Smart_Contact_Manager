package com.Gaowtam.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.Gaowtam.entities.User;
import com.Gaowtam.helpers.Message;
import com.Gaowtam.helpers.MessageType;
import com.Gaowtam.repositories.UserRepo;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {
    // verify email

    @Autowired
    private UserRepo userRepo;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("token") String token, HttpSession session) {
        System.out.println("Verify Email");
        User user = userRepo.findByEmailToken(token).orElse(null);

        if (user != null) {
            // user fetch
            if (user.getEmailToken().equals(token)) {
                user.setEmmailVerified(true);
                user.setEnabled(true);
                userRepo.save(user);

                session.setAttribute("message", Message.builder()
                        .type(MessageType.green)
                        .content("Your email is verified. Now you can login")
                        .build());

                return "success_page";
            }
            session.setAttribute("message", Message.builder()
                    .type(MessageType.red)
                    .content("Email not verified ! Token is not associated with user .")
                    .build());
            return "error_page";
        }
        session.setAttribute("message", Message.builder()
                .type(MessageType.red)
                .content("Email not verified ! Token is not associated with user .")
                .build());
        return "error_page";
    }
}
