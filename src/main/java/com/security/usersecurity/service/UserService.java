package com.security.usersecurity.service;

import com.security.usersecurity.model.UserModel;
import com.security.usersecurity.repository.RoleModelRepo;
import com.security.usersecurity.repository.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserModelRepo userModelRepo;

    @Autowired
    RoleModelRepo roleModelRepo;

    public UserModel getUserByEmail(String email){
        UserModel user =  userModelRepo.findByEmail(email);
        return user;
    }
}
