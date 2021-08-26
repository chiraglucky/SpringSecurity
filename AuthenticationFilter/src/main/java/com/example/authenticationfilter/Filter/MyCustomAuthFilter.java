package com.example.authenticationfilter.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


//By default BasicAuthenticationFilter extends OncePerRequestFilter
//so we can create custom filter by just extends OncePerRequestFilter
//i.e. class MyCustomAuthFilter extends OncePerRequestFilter

//Filter -> GenericFilterBean -> OncePerRequestFilter -> BasicCuthenticationFilter ..etc
//                                                    -> MyCustomAuthFilter
@Component
public class MyCustomAuthFilter implements Filter {

    @Autowired
    AuthenticationManager authenticationManager;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        //1.do filter based upon request
        //2.create Authentication object
        //3.delegate Auth Object to AuthenticationManager

        //based upon request
        //typecast to HttpServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String headerValue = request.getHeader("auth_key");

        //create Authentication Object
        Authentication authentication = new MyCustomAuthToken(headerValue, null);

        //delegates to AuthenticationManager(Interface)
        try {
            Authentication authPrincipal = authenticationManager.authenticate(authentication);

            //******************************


            //check authentication object is authenticated or not
            //if yes then set object to SecurityContextHolder for future use
            if (authPrincipal.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authPrincipal);
                filterChain.doFilter(servletRequest, servletResponse);
            }
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid User");
        }
    }
}
