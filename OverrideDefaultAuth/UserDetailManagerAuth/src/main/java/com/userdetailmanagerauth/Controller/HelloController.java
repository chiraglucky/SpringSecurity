package com.userdetailmanagerauth.Controller;

import com.userdetailmanagerauth.Entity.MyUser;
import com.userdetailmanagerauth.Service.MyUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    MyUserService myUserService;

    @GetMapping("/hello")
    public String helloMethod(){
        return "Hello World";
    }

    @PostMapping("/user")
    public void addUser(@RequestBody MyUser myUser){
        myUserService.addUser(myUser);
    }
}
