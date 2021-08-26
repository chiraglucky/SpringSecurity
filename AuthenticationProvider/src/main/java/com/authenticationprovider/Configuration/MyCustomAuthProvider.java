package com.authenticationprovider.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyCustomAuthProvider implements AuthenticationProvider {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1.authenticate Authentication Object for valid user
        //2.throws Exception if invalid user
        //3.return null if supports method return false

        //user input credentials
        String password=authentication.getCredentials().toString();

        //get InMemoryUserDetails by user input username
        UserDetails userDetails=userDetailsService.loadUserByUsername(authentication.getName());

        if(userDetails!=null && passwordEncoder.matches(password,userDetails.getPassword())){
            return new UsernamePasswordAuthenticationToken(
              authentication.getName(),
              authentication.getCredentials().toString()
            );
        }

        return null;
    }

    //calls before authenticate method
    //which authentication provider is suitable for our request like:
     //face-detection,bio metrics,UsernamePassword
    //if not match(return false) then authenticate method return null
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}
