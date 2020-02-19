package com.insurance_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

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

    private Date fromDate;

    private Date toDate;

      @Column(name = "policyNumber")
      private String policyNumber;

      @Transient
      private int insuranceCost;

      @Transient
      private int numberOfDays;

      private Product product;

      @Column(name = "status")
      private String status;

      @Column(name = "payment_status")
      private String paymentStatus;

      @Column
      private Date registerDate;

      @PrePersist
      void registerDate() {
          this.registerDate = new Date();
      }

      private User createdBy;

    @JsonIgnore
    @ManyToMany(mappedBy = "insurances")
    private List<User> users;


}

/*
-fromDate,toDate,policyNumber, insuranceCost, numberOfDays,product,status, paymentStatus, registerDate,client,createdBy;
 */
