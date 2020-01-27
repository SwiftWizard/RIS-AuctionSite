package com.ris.ris.project.listeners;

import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    @Autowired
    HttpSession session;

    @Autowired
    private UserRepository ur;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent authenticationSuccessEvent) {
        Authentication auth = authenticationSuccessEvent.getAuthentication();
        if(auth != null){
            Object principal = auth.getPrincipal();
            if(principal instanceof UserDetails){ //Note to self: This check, is it really necessary?
                UserDetails userDetails = (UserDetails) principal;
                session.setAttribute("user", ur.findByUsername(userDetails.getUsername()));
            }
        }else{
            //TODO handle in some other way...
            System.err.println("Authentication null?!");
        }
    }
}
