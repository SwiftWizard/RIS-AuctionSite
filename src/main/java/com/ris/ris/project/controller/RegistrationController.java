package com.ris.ris.project.controller;

import com.ris.ris.project.model.*;
import com.ris.ris.project.repository.AddressRepository;
import com.ris.ris.project.repository.AuthorityRepository;
import com.ris.ris.project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    UserRepository ur;

    @Autowired
    AddressRepository ar;

    @Autowired
    AuthorityRepository atr;


    @RequestMapping(value = "/newUser")
    public String getCountriesAndPostalCodes(Model model){
        model.addAttribute("countries", Country.values());
        model.addAttribute("postalCodes", PostalCode.values());
        return "register/newUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(String fName, String lName, String dateOfBirth, String uName, String email, String phone, Country country, PostalCode pCode, String city, String address, String password, Model model){
        if(ur.findByUsername(uName) == null){
            User user = new User();
            user.setUsername(uName);
            user.setFirstName(fName);
            user.setLastName(lName);

            try{
                user.setDateOfBirth(LocalDate.parse(dateOfBirth));
            }catch (Exception e){
                user.setDateOfBirth(null);//TODO Handle a little bit better
            }

            user.setAccountCreationDate(LocalDate.now());
            user.setEmail(email);
            user.setPhoneNumber(phone);
            user.setPassword(password);//Encryption done at setter!

            Address adr = new Address();
            adr.setCountry(country);
            adr.setPostalCode(pCode);
            adr.setCity(city);
            adr.setAddress(address);

            ar.save(adr);

            Authority auth = new Authority();
            user.addAuthority(auth);
            user.setAddress(adr);

            ur.save(user);
            atr.save(auth);

            String message = String.format("User %s registered successfully", fName);
            model.addAttribute("message", message);

            return "/index";

        }else{
            String message = String.format("A user with the entered username (%s) already exists. Please chose some other username.", uName);
            model.addAttribute("message", message);
            return "register/newUser";
        }
    }
}
