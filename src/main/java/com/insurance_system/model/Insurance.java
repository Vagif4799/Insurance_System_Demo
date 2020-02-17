package com.insurance_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "insurances")
public class Insurance {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "insurance_id")
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, message = "This field can't be empty.")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private List<User> users;

    @Column
    private Date createdDate;

    @PrePersist
    void createdAt() {
        this.createdDate = new Date();
    }

}
