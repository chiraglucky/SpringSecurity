package com.example.authenticationfilter.Configuration;

import com.example.authenticationfilter.Filter.MyCustomAuthFilter;
import com.example.authenticationfilter.Provider.MyCutomAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyCutomAuthProvider myCutomAuthProvider;

    @Autowired
    MyCustomAuthFilter myCustomAuthFilter;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(myCutomAuthProvider);
    }

    //adding custom filter to filter chain
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        for adding filter in filter chain
//        http.addFilter(myCustomAuthFilter);

//        To replace any filter to custom filter
        http.addFilterAt(myCustomAuthFilter, BasicAuthenticationFilter.class);
        http.authorizeRequests().anyRequest().permitAll();
    }

    //for autowired AuthenticationManager
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
