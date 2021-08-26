package com.multipleauthentication.Provider;

import com.multipleauthentication.Auth.UnamePasswordAuthToken;
import com.multipleauthentication.Service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UnamePasswordAuthProvider implements AuthenticationProvider {

    @Autowired
    MyUserDetailsService myUserDetailsService;

    @Autowired
    PasswordEncoder encoder;

    @Override
    public Authentication authenticate(Authentication authentication){
        //1.authenticate Authentication Object for valid user
        //2.throws Exception if invalid user
        //3.return null if supports method return false

        UserDetails userDetails=myUserDetailsService.loadUserByUsername(authentication.getName());

        if(encoder.matches(authentication.getCredentials()+"",userDetails.getPassword())){
            return new UnamePasswordAuthToken(userDetails.getUsername(),userDetails.getPassword(),userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Failed user Authentication");
    }

    //calls before authenticate method
    //which authentication provider is suitable for our request like:
    //face-detection,bio metrics,UsernamePassword
    //if not match(return false) then authenticate method return null
    @Override
    public boolean supports(Class<?> authentication) {
        return UnamePasswordAuthToken.class.equals(authentication);
    }
}


