package com.security.usersecurity.dataloader;

import com.security.usersecurity.model.RoleModel;
import com.security.usersecurity.model.UserModel;
import com.security.usersecurity.repository.RoleModelRepo;
import com.security.usersecurity.repository.UserModelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    @Autowired
    private RoleModelRepo roleModelRepo;

    @Autowired
    private UserModelRepo userModelRepo;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        roleModelRepo.save(new RoleModel("BIDDER"));
        roleModelRepo.save(new RoleModel("APPROVER"));

        userModelRepo.save(new UserModel(1,"bidder1","companyOne","bidderemail@gmail.com","bidder123$",new RoleModel(1)));
        userModelRepo.save(new UserModel(2,"bidder2","companyTwo","bidderemail2@gmail.com", "bidder789$",new RoleModel(1)));
        userModelRepo.save(new UserModel(3,"approver","defaultCompany","approveremail@gmail.com", "approver123$",new RoleModel(2)));

    }
}
