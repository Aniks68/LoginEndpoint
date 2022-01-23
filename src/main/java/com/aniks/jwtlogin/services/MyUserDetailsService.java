package com.aniks.jwtlogin.services;

import com.aniks.jwtlogin.model.Person;
import com.aniks.jwtlogin.model.PersonRole;
import com.aniks.jwtlogin.repository.PersonRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
        /* Should load user by username from database or source of choice*/
//        return new Person(27L, "Samuel", "Jachike", "sam@el.com", "12345", "chike", ADMIN);

        return personRepository.findByUsername(username).orElse(null);
    }

    public Person saveUser(Person person) {
        Person newPerson = personRepository.findByUsernameOrEmail(person.getUsername(), person.getEmail()).orElse(null);
        if(newPerson == null) {
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
