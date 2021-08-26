package com.multipleauthentication.Provider;

import com.multipleauthentication.Auth.SecretKeyAuthToken;
import com.multipleauthentication.Model.UserSecretKey;
import com.multipleauthentication.Repository.UserSecretKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Optional;

@Component
public class SecretKeyAuthProvider implements AuthenticationProvider {

    @Autowired
    UserSecretKeyRepository userSecretKeyRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        UserSecretKey user =userSecretKeyRepository.findByUsername(authentication.getName()).orElse(new UserSecretKey());

        if(passwordEncoder.matches(authentication.getCredentials().toString(),user.getPin())){
            return new SecretKeyAuthToken(authentication.getName(),
                    authentication.getCredentials(),
                    Arrays.asList(()->"read"));
        }
        throw new BadCredentialsException("Failed SecretKey Authentication");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return SecretKeyAuthToken.class.equals(authentication);
    }
}
