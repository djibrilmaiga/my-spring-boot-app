package com.mycompany.fitmanager.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @GetMapping("/manager")
    public String getManager(){
        return "Welcome, Manager";
    }

    @GetMapping("/admin")
    public String getAdmin(){
        return "Welcome, Admin";
    }
}
