package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.model.UserSignUpDTO;
import com.bohdanbest.shoestore.security.AuthenticationInfoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {

    @Autowired
    private AuthenticationInfoFacade authFacade;

    @GetMapping("/register")
    public ModelAndView showRegisterForm() {
        ModelAndView modelAndView = new ModelAndView("pages/sign-up");
        modelAndView.addObject("userSignUpDTO", new UserSignUpDTO());
        modelAndView.addObject("title", "Register");
        modelAndView.addObject("authFacade", authFacade);
        return modelAndView;
    }
}