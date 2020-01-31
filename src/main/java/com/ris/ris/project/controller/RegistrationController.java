package com.ris.ris.project.controller;

import com.ris.ris.project.model.*;
import com.ris.ris.project.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.*;
import java.time.LocalDate;
import java.util.Set;


@Controller
@RequestMapping("/register")
public class RegistrationController {
    @Autowired
    HttpSession session;

    @Autowired
    UserRepository ur;

    @Autowired
    AddressRepository ar;

    @Autowired
    AuthorityRepository atr;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @RequestMapping(value = "/newUser")
    public String getCountriesAndPostalCodes(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register/newUser";
    }

    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    public String registerUser(User user, BindingResult bindingResult, Model model){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<User>> violations = validator.validate(user);
        if(!violations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("Please fill in the flowing fields properly: \n");
            for(ConstraintViolation<User> violation : violations){
                sb.append("->");
                sb.append(violation.getMessage());
                sb.append("\n");
            }
            model.addAttribute("message", sb.toString());
            return "/register/newUser";
        }else{
            if(ur.findByUsername(user.getUsername()) == null) { //If no one exist with entered username proceed
                user.setAccountCreationDate(LocalDate.now());
                Authority auth = new Authority();
                user.addAuthority(auth);
                ur.save(user);
                atr.save(auth);
                model.addAttribute("user", user);
                model.addAttribute("newAddress", new Address());
                model.addAttribute("countries", Country.values());
                model.addAttribute("postalCodes", PostalCode.values());
                return "/register/addAddress";
            }
            String message = String.format("A user with the entered username (%s) already exists.\nPlease chose some other username.", user.getUsername());
            model.addAttribute("message", message);
            return "/register/newUser";
        }
    }

    @RequestMapping(value = "/newAddress")
    public String newAddress(Model model){
        model.addAttribute("user", session.getAttribute("user"));
        model.addAttribute("newAddress", new Address());
        model.addAttribute("countries", Country.values());
        model.addAttribute("postalCodes", PostalCode.values());
        return "/register/addAddress";
    }

    @RequestMapping(value = "/addAddress", method = RequestMethod.POST)
    public String addAddress(Address newAddress, BindingResult bindingResult, Model model){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Set<ConstraintViolation<Address>> violations = validator.validate(newAddress);
        if(!violations.isEmpty()){
            StringBuilder sb = new StringBuilder();
            sb.append("Please fill in the flowing fields properly: \n");
            for(ConstraintViolation<Address> violation : violations){
                sb.append("->");
                sb.append(violation.getMessage());
                sb.append("\n");
            }
            model.addAttribute("message", sb.toString());
            model.addAttribute("countries", Country.values());
            model.addAttribute("postalCodes", PostalCode.values());
            return "/register/addAddress";
        }else{
            User user = (User) model.getAttribute("user");
            if(user == null){
                //in this case a already registered user whats to add/change his address
                user = (User) session.getAttribute("user");
            }
            ar.save(newAddress);
            user.setAddress(newAddress);
            ur.save(user);
            return "/index";
        }
    }

//    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
//    public String registerUser(String fName, String lName, String dateOfBirth, String uName, String email, String phone, Country country, PostalCode pCode, String city, String address, String password, Model model){
//        if(ur.findByUsername(uName) == null){
//            User user = new User();
//            user.setUsername(uName);
//            user.setFirstName(fName);
//            user.setLastName(lName);
//
//            try{
//                user.setDateOfBirth(LocalDate.parse(dateOfBirth));
//            }catch (Exception e){
//                user.setDateOfBirth(null);//TODO Handle a little bit better
//            }
//
//            user.setAccountCreationDate(LocalDate.now());
//            user.setEmail(email);
//            user.setPhoneNumber(phone);
//            user.setPassword(password);//Encryption done at setter!
//
//            Address adr = new Address();
//            adr.setCountry(country);
//            adr.setPostalCode(pCode);
//            adr.setCity(city);
//            adr.setAddress(address);
//
//            ar.save(adr);
//
//            Authority auth = new Authority();
//            user.addAuthority(auth);
//            user.setAddress(adr);
//
//            ur.save(user);
//            atr.save(auth);
//
//            String message = String.format("User %s registered successfully", fName);
//            model.addAttribute("message", message);
//
//            return "/index";
//
//        }else{
//            String message = String.format("A user with the entered username (%s) already exists. Please chose some other username.", uName);
//            model.addAttribute("message", message);
//            return "register/newUser";
//        }
//    }
}
