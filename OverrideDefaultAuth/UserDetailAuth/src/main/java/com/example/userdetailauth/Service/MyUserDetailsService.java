package com.example.userdetailauth.Service;

import com.example.userdetailauth.Entity.MyUser;
import com.example.userdetailauth.Repository.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    MyUserRepository myUserRepository;

    //using this we can only load user details
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        MyUser user =myUserRepository.findByUsername(s).get();
        return new MyUserDetails(user);
    }
}
