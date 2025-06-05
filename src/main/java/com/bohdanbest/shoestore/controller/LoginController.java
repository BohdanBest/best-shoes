package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.model.LoginDTO;
import com.bohdanbest.shoestore.security.AuthenticationInfoFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

    @Autowired
    private AuthenticationInfoFacade authFacade;

    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(name = "error", required = false) String error,
                                      @RequestParam(name = "logout", required = false) String logout,
                                      @RequestParam(name = "success", required = false) String success) {
        ModelAndView modelAndView = new ModelAndView("pages/login");
        modelAndView.addObject("loginDTO", new LoginDTO());
        modelAndView.addObject("title", "Login");
        modelAndView.addObject("authFacade", authFacade);

        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password");
        }
        if (logout != null) {
            modelAndView.addObject("message", "You have been logged out successfully");
        }
        if (success != null) {
            modelAndView.addObject("message", success);
        }

        return modelAndView;
    }
}