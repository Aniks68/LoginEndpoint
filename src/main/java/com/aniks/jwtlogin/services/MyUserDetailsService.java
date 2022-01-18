package com.aniks.jwtlogin.services;

import com.aniks.jwtlogin.model.Person;
import com.aniks.jwtlogin.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        /* Should load user by username from database or source of choice*/
//        return new Person("Samuel", "Jachike", "sam@el.com", "12345", "chike");

        return personRepository.findByUsername(username).orElse(null);
    }
}
