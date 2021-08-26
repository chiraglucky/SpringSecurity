package com.userdetailmanagerauth.Service;

import com.userdetailmanagerauth.Entity.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

@Service
public class MyUserService {

    @Autowired
    UserDetailsManager userDetailsManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    public void addUser(MyUser myUser){
        myUser.setPassword(passwordEncoder.encode(myUser.getPassword()));
        userDetailsManager.createUser(myUser);
    }
}
