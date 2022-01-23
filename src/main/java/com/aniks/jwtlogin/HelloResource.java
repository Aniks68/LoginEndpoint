package com.aniks.jwtlogin;

import com.aniks.jwtlogin.model.AuthRequest;
import com.aniks.jwtlogin.model.AuthResponse;
import com.aniks.jwtlogin.model.Person;
import com.aniks.jwtlogin.services.MyUserDetailsService;
import com.aniks.jwtlogin.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
public class HelloResource {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    MyUserDetailsService userService;

    @Autowired
    JwtUtils jwtTokenUtils;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody Person person) {
        System.out.println("Register request: " + person);
        Person authenticated = userService.saveUser(person);
        return authenticated == null ? "Username or email already registered!" : "Succesfully registered: " + authenticated.getId() + " . Authorities: " + authenticated.getRole().getGrantedAuthorities();
    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthRequest authRequest,
                                                       HttpServletResponse response) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }

        final Person userDetails = userService
                .loadUserByUsername(authRequest.getUsername());

        final String jwt = jwtTokenUtils.generateToken(userDetails);

        response.addHeader("Authorization", "Bearer " + jwt);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }

    @GetMapping("/users")
    public String getAll() {
        Person person = userService.findById(1L);
        person.setUsername("nenye");
        userService.saveUser(person);
        return "Registered users: " + userService.findAll();
    }
}
