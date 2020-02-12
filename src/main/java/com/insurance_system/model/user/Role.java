package com.insurance_system.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Data
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Roles_Privileges",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id")
    )
    private List<Privilege> privileges;

    @OneToMany(mappedBy = "role")
    private List<User> users;

    public Role() {
    }


    public Role(String name, List<Privilege> privileges, List<User> users) {
        this.name = name;
        this.privileges = privileges;
        this.users = users;
    }


}
