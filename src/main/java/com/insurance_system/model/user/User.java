package com.insurance_system.model.user;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column(unique = true, nullable = false)
    private String username;

    @Column
    private String email;

    @Column(length = 60)
    private String password;

    private boolean enabled;

    private boolean accountNonExpired;

    private boolean credentialsNonExpired;

    private boolean accountNonLocked;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {

        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;

    }

    public User(String firstName, String lastName, String username, String email, String password, Role role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
        this.accountNonExpired = true;
        this.credentialsNonExpired = true;
        this.accountNonLocked = true;
        this.enabled = true;
    }

}