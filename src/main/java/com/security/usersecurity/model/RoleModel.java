package com.security.usersecurity.model;

import org.apache.catalina.User;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "rolename",unique = true)
    private String rolename;

    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<UserModel> user1;

    public RoleModel(String rolename) {
        this.rolename = rolename;
    }

    public RoleModel(){

    }

    public RoleModel(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    @Override
    public String toString() {
        return "RoleModel{" +
                "id=" + id +
                ", rolename='" + rolename + '\'' +
                '}';
    }
}
