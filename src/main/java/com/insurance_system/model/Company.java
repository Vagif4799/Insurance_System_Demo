package com.insurance_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.awt.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "companies")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_id")
    private Long id;

    @Column(name = "company_name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column
    private String mobileNumber;

    @Column
    private String officeNumber;

    @Column
    private Date createdDate;

    @PrePersist
    void createdAt() {
        this.createdDate = new Date();
    }

    @Column(columnDefinition="varchar(1000)")
    @NotBlank
    private String logo;

    @Column
    private boolean status;

    @JsonIgnore
    @OneToMany (mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> users;

}


//name,address,mobileNumber, officeNumber, createdDate, logo,status;