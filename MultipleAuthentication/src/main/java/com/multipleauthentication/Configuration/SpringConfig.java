package com.multipleauthentication.Configuration;

import com.multipleauthentication.Filter.MyCustomAuthFilter;
import com.multipleauthentication.Provider.SecretKeyAuthProvider;
import com.multipleauthentication.Provider.UnamePasswordAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SpringConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyCustomAuthFilter myCustomAuthFilter;

    @Autowired
    UnamePasswordAuthProvider unamePasswordAuthProvider;

    @Autowired
    SecretKeyAuthProvider secretKeyAuthProvider;

    @Bean
    public UserDetailsService MyUserDetailsService(){
         return null;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return NoOpPasswordEncoder.getInstance();
    }

    //for Autowire AuthenticationManager
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(unamePasswordAuthProvider)
                .authenticationProvider(secretKeyAuthProvider);
    }

    //set Custom Filter to Filterchain
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAt(myCustomAuthFilter, BasicAuthenticationFilter.class);
    }
}
