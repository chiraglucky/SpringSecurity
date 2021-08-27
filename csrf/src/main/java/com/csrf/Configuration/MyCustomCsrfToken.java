package com.csrf.Configuration;

import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class MyCustomCsrfToken implements CsrfTokenRepository {
    @Override
    public CsrfToken generateToken(HttpServletRequest httpServletRequest) {
        CsrfToken csrfToken=new DefaultCsrfToken("X-CSRF-TOKEN","_csrf",
                "ABCD123456");
        return csrfToken;
    }

    @Override
    public void saveToken(CsrfToken csrfToken, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {

    }

    @Override
    public CsrfToken loadToken(HttpServletRequest httpServletRequest) {
        return null;
    }
}
