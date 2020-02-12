package com.insurance_system.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
public class Privilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "privilege_id")
    private Long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "privileges")
    private List<Role> roles;

    public Privilege() {
    }

    public Privilege(String name, List<Role> roles) {
        this.name = name;
        this.roles = roles;
    }



}
