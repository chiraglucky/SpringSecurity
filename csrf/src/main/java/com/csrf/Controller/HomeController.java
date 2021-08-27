package com.csrf.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/home")
    public String homeMethod(){
        return "home.html";
    }

    @PostMapping("/save-data")
    public String formSubmit(String username){
        System.out.println(username);
        return "home.html";
    }
}
