package com.aniks.jwtlogin.controllers;

import com.aniks.jwtlogin.model.AuthRequest;
import com.aniks.jwtlogin.model.AuthResponse;
import com.aniks.jwtlogin.model.Person;
import com.aniks.jwtlogin.services.MyUserDetailsService;
import com.aniks.jwtlogin.util.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/pod")
public class LoginController {

    private final AuthenticationManager authenticationManager;

    final
    MyUserDetailsService userService;

    final
    JwtUtils jwtTokenUtils;

    public LoginController(AuthenticationManager authenticationManager, MyUserDetailsService userService, JwtUtils jwtTokenUtils) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenUtils = jwtTokenUtils;
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String getAdmin() {
        return "This is an admin";
    }

    @GetMapping("/premium")
    @PreAuthorize("hasRole('ROLE_PREMIUM')")
    public String getUser() {
        return "This is a premium user";
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody Person person) {
        System.out.println("Register request: " + person);
        Person authenticated = userService.saveUser(person);
        return authenticated == null ? "Username or email already registered!" : "Successfully registered: " + authenticated.getId() + " . Authorities: " + authenticated.getRole().getGrantedAuthorities();
    }

    @PostMapping("/login")
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
        return "Registered users: " + userService.findAll();
    }
}
