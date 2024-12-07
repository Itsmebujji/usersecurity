package com.security.usersecurity.service;

import com.security.usersecurity.model.UserModel;
import com.security.usersecurity.repository.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginService implements UserDetailsService {

    @Autowired
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserModel userModel = userService.getUserByEmail(email);
        List<GrantedAuthority> authorities = buildUserAuthority(userModel.getRole().getRolename());
        UserDetails userDetails = buildUserForAuthentication(userModel, authorities);
        return userDetails;
    }

    public UserDetails buildUserForAuthentication(UserModel user, List<GrantedAuthority> authorities){
        UserDetails newUser = User.builder().username(user.getEmail())
                .password(user.getPassword())
                .authorities(authorities)
                .build();
        return newUser;
    }

    public List<GrantedAuthority> buildUserAuthority(String userRole){
        return List.of(new SimpleGrantedAuthority(userRole));
    }
}
