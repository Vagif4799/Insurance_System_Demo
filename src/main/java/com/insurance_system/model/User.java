package com.insurance_system.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, message = "This field can't be empty.")
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, message = "This field can't be empty.")
    private String username;

    @Column
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "Password is required")
    private String password;

    @Column(name = "user_status")
    private boolean status;

    @ManyToOne
    @JoinTable(name = "r_company_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "company_id", referencedColumnName = "company_id")
    )
    private Company company;

    @Column
    private Date registerDate;

    @Column
    private String phoneNumber;

    @Column
    private Date lastLoginDate;

    @Enumerated(EnumType.STRING)
    private Role role;


}
