package com.multipleauthentication.Provider;

import com.multipleauthentication.Auth.TokenAuthToken;
import com.multipleauthentication.Repository.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    TokenManager tokenManager;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String token=authentication.getName();
        boolean flag=tokenManager.contains(token);
        if(flag){
            return new TokenAuthToken(token,null,null);
        }
        throw new BadCredentialsException("Invalid Token");
    }

    //calls before authenticate method
    //which authentication provider is suitable for our request like:
    //face-detection,bio metrics,UsernamePassword
    //if not match(return false) then authenticate method return null
    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthToken.class.equals(authentication);
    }
}
