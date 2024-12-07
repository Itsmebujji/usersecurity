package com.security.usersecurity.repository;

import com.security.usersecurity.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserModelRepo extends JpaRepository<UserModel,Integer> {
    @Query(value = "SELECT * FROM users u WHERE u.email=?1", nativeQuery = true)
    UserModel findByEmail(String email);

//    UserModel findByEmail(String email);
}
