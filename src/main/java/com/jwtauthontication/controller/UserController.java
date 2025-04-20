package com.jwtauthontication.controller;

import com.jwtauthontication.config.JWTUtil;
import com.jwtauthontication.model.User;
import com.jwtauthontication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")

public class UserController {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody User user){
        return ResponseEntity.ok(userService.signUp(user));
    }

    @PostMapping("/signin")
    public ResponseEntity<String> generateToken(@RequestBody User user){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getUserPassword()));
        return ResponseEntity.ok(jwtUtil.generateToken(user.getUserName()));
    }
}
