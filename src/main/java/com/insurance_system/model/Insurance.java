package com.insurance_system.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


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
    private Date registerDate;

    @PrePersist
    void registerDate() {
        this.registerDate = new Date();
    }

    @Column(name = "to_date")
    private Date toDate;

    @Column(name = "from_date")
    private Date fromDate;
    {
        fromDate = registerDate;
    }

    @Column(name = "policy_number")
    private String policyNumber;

    {
        byte[] array = new byte[5]; // length is bounded by 5
        new Random().nextBytes(array);
        String generatedString = new String(array, StandardCharsets.UTF_8);
        policyNumber = getClients().get(0).getFIN() + generatedString;
    }

    @Column(name = "number_of_days")
    private int numberOfDays;

    {
        numberOfDays = (int)((toDate.getTime()-fromDate.getTime())/(24*60*60*1000));
    }

    @Column(name = "status")
    private String status;
    {
        Date now = new Date();
        if (now.equals(registerDate)){
            status = "ACTIVE";
        } else if (now.compareTo(registerDate) > 0 && now.compareTo(toDate) < 0) {
            status = "IN_PROGRESS";
        } else {
            status = "DEACTIVATED";
        }
    }

    // Should be redefined after Payment module
    @Column(name = "payment_status")
    private String paymentStatus;

    {
        paymentStatus = "PAID";
    }

    @Column(name = "insurance_cost")
    private int insuranceCost;

    private Product product;

    @JsonIgnore
    @ManyToMany(mappedBy = "insurances")
    private List<Client> clients;

    @JsonIgnore
    @ManyToMany(mappedBy = "insurances")
    private List<User> createdBy;





}



