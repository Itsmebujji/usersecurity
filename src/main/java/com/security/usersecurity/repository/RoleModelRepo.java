package com.security.usersecurity.repository;

import com.security.usersecurity.model.RoleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleModelRepo extends JpaRepository<RoleModel, Integer> {
}
