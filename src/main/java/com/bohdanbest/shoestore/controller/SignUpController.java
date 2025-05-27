package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.model.UserSignUpDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

    @GetMapping("/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("pages/sign-up");
        modelAndView.addObject("title", "Register - Shoe Store");
        modelAndView.addObject("userRegistrationDTO", new UserSignUpDTO());
        return modelAndView;
    }
}