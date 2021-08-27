package com.csrf.Configuration;

import com.csrf.Filter.MyCustomCsrfFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfFilter;

@Configuration
public class SpringConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyCustomCsrfFilter myCustomCsrfFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //To disable CSRF
        //http.csrf().disable();

        //ignore csrf for url which start from pattern /abc
        //http.csrf(csrf->csrf.ignoringAntMatchers("/abc/**"));

        //for custom csrf token
        http.csrf(csrf->csrf.csrfTokenRepository(new MyCustomCsrfToken()));

        http.addFilterAfter(myCustomCsrfFilter, CsrfFilter.class);
    }
}
