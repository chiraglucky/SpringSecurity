package com.multipleauthentication.Filter;

import com.multipleauthentication.Auth.UnamePasswordAuthToken;
import com.multipleauthentication.Auth.SecretKeyAuthToken;
import com.multipleauthentication.Model.UserSecretKey;
import com.multipleauthentication.Repository.UserSecretKeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;


//By default BasicAuthenticationFilter extends OncePerRequestFilter
//so we can create custom filter by just extends OncePerRequestFilter
//i.e. class MyCustomAuthFilter extends OncePerRequestFilter

//Filter -> GenericFilterBean -> OncePerRequestFilter -> BasicCuthenticationFilter ..etc
//                                                    -> MyCustomAuthFilter
@Component
public class MyCustomAuthFilter extends OncePerRequestFilter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserSecretKeyRepository userSecretKeyRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1.do filter based upon request
        //2.create Authentication object
        //3.delegate Auth Object to AuthenticationManager

        String username = request.getHeader("username");
        String password = request.getHeader("password");

        String key = request.getHeader("secret-key");

        if (key == null) {
            //authenticate using username and password
            Authentication authentication = new UnamePasswordAuthToken(username, password);
            Authentication authPrincipal = authenticationManager.authenticate(authentication);

            //generate secret key and save into db
            UserSecretKey secretKey = new UserSecretKey();
            secretKey.setPin((new Random().nextInt(999) * 1000) + "");
            secretKey.setUsername(username);
            userSecretKeyRepository.save(secretKey);


        } else {
            //authenticate using key
            Authentication authentication=new SecretKeyAuthToken(username,key);
            Authentication authPrincipal=authenticationManager.authenticate(authentication);

            //generate token
            response.setHeader("Authorization", UUID.randomUUID().toString());
        }


    }

    //enable request at runtime
    //by default disable
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getServletPath().equals("/hello");
    }
}
