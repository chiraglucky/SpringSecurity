package com.oauth2.Configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;

//AuthorizationServer
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    AuthenticationManager authenticationManager;

    //from which client(App) request is coming to AuthorizationServer
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().
                withClient("myclient").
                secret("1234").
                scopes("read").
                authorizedGrantTypes("password","refresh_token").//password //how user interact with AuthorizationServer
                and().

                withClient("myclient3").
                secret("1234").
                scopes("info").
                authorizedGrantTypes("client_credentials").  //client credentials
                and().

                withClient("myclient2").
                secret("1234").
                scopes("read").
                authorizedGrantTypes("authorization_code"). //authorization_code
                redirectUris("http://localhost:8083");//which client issue a token from AuthServer
    }

    //move user to Authorization Server
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager);
    }
}
