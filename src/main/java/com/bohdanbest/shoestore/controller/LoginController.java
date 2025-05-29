package com.bohdanbest.shoestore.controller;

import com.bohdanbest.shoestore.model.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/login")
    public ModelAndView showLoginForm(@RequestParam(value = "error", required = false) String error,
                                      @RequestParam(value = "logout", required = false) String logout) {
        ModelAndView modelAndView = new ModelAndView("pages/login");
        modelAndView.addObject("title", "Login - Shoe Store");
        modelAndView.addObject("loginDTO", new LoginDTO());
        if (error != null) {
            modelAndView.addObject("error", "Invalid username or password");
        }
        if (logout != null) {
            modelAndView.addObject("message", "You have been logged out successfully");
        }
        return modelAndView;
    }
}
