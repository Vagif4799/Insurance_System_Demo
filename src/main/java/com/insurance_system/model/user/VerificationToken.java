package com.insurance_system.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
@Table(name = "PassworVerificationToken")
public class VerificationToken {

    @Transient
    private final int EXPIRY_DATE = 60*24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String token;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public VerificationToken() {
        this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
    }

    private Date calculateExpiryDate(int expiry_date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiry_date);
        return new Date(calendar.getTime().getTime());
    }

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
    }


}
