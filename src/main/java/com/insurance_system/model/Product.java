package com.insurance_system.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "product_id")
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, message = "This field can't be empty.")
    private String name;

    @Column
    private Date createdDate;

    @PrePersist
    void createdAt() {
        this.createdDate = new Date();
    }

}
