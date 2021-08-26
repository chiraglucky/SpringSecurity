package com.example.authenticationfilter.Provider;

import com.example.authenticationfilter.Filter.MyCustomAuthToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class MyCutomAuthProvider implements AuthenticationProvider {

    @Value("${secret_key}")
    String secretKey;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        //1.authenticate Authentication Object for valid user
        //2.throws Exception if invalid user
        //3.return null if supports method return false

        if(secretKey.equals(authentication.getName())){
            return new MyCustomAuthToken(null,null,null);
        }

        return null;
    }

    //calls before authenticate method
    //which authentication provider is suitable for our request like:
    //face-detection,bio metrics,UsernamePassword
    //if not match(return false) then authenticate method return null
    @Override
    public boolean supports(Class<?> authentication) {
        return MyCustomAuthToken.class.equals(authentication);
    }
}
