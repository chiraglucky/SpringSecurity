package com.multipleauthentication.Auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;


public class UnamePasswordAuthToken extends UsernamePasswordAuthenticationToken {
    public UnamePasswordAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public UnamePasswordAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }
}
