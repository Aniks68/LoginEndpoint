package com.aniks.jwtlogin.services;

import com.aniks.jwtlogin.model.Person;
import com.aniks.jwtlogin.model.PersonRole;
import com.aniks.jwtlogin.repository.PersonRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static com.aniks.jwtlogin.model.PersonRole.*;

@Service
public class MyUserDetailsService implements UserDetailsService {

    PersonRepository personRepository;

    public MyUserDetailsService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person loadUserByUsername(String username) throws UsernameNotFoundException {

        Person person = personRepository.findByUsername(username).orElse(null);
//        List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
//        String[] authStrings = personRepository.getAuthorities().split(", ");
//        for(String authString : authStrings) {
//            authorities.add(new SimpleGrantedAuthority(authString));
//        }
//
//        UserDetails ud = new User(account.getUsername(), account.getPassword(), authorities);
        return person;
    }

    public Person saveUser(Person person) {
        Person newPerson = personRepository.findByUsernameOrEmail(person.getUsername(), person.getEmail()).orElse(null);
        if(newPerson == null) {
            person.setPosition(person.getRole().name());
            personRepository.save(person);
            return person;
        }
        return null;
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person findById(Long id) {
        return personRepository.findById(id).orElse(null);
    }
}
