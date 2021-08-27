package com.multipleauthentication.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping("/hello")
    public String helloMethod(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        System.out.println(auth.getName());
        return "Hello multiple authentication";
    }
}
