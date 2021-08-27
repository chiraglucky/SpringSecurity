package com.multipleauthentication.Repository;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class TokenManager {
    private Set<String> tokenList;

    public TokenManager(){
         tokenList=new HashSet<String>();
    }

    public void add(String token){
        tokenList.add(token);
    }

    public boolean contains(String token){
        return tokenList.contains(token);
    }
}
