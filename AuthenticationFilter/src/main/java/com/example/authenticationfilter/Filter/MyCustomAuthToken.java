package com.example.authenticationfilter.Filter;

//UsernamePasswordAuthenticationToken extends AbstractAuthenticationToken
//AbstractAuthenticationToken implements Authentication

//So MyCustomAuthToke extends UsernamePasswordAuthenticationToken(with 2 constructor)


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class MyCustomAuthToken extends UsernamePasswordAuthenticationToken {

    public MyCustomAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public MyCustomAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

}
