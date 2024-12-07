package com.security.usersecurity.controller;

import com.security.usersecurity.dto.LoginDTO;
import com.security.usersecurity.model.UserModel;
import com.security.usersecurity.repository.UserModelRepo;
import com.security.usersecurity.security.JwtUtility;
import com.security.usersecurity.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    LoginService loginService;

    @Autowired
    JwtUtility jwtUtility;

    @Autowired
    UserModelRepo userModelRepo;

    @PostMapping("/login")
    public Object login(@RequestBody LoginDTO loginDTO){
        try{
            UserModel user = userModelRepo.findByEmail(loginDTO.getEmail());
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            UserDetails userDetails = loginService.loadUserByUsername(user.getEmail());
            String token = jwtUtility.generateToken(userDetails);
            Map<String, Object> object = new HashMap<String, Object>();
            object.put("jwt",token);
            object.put("status", 200);
            ResponseEntity<Object> response = new ResponseEntity<>(object, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
}
