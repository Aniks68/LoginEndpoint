package com.aniks.jwtlogin.services;

import com.aniks.jwtlogin.model.Person;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Should load user by username from database or source of choice*/
        return new Person("Samuel", "Jachike", "sam@el.com", "12345", "chike");
    }
}
