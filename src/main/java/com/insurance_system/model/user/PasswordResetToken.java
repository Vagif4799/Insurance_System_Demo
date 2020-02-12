package com.insurance_system.model.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

@Data
@Entity
public class PasswordResetToken {

    @Transient
    private final int EXPIRY_DATE = 24*60;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String token;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", foreignKey = @ForeignKey(foreignKeyDefinition = "user_fk"))
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;

    public PasswordResetToken() {
        this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
    }

    public PasswordResetToken(String token, User user) {
        this.user = user;
        this.token = token;
        this.expiryDate = calculateExpiryDate(EXPIRY_DATE);
    }

    private Date calculateExpiryDate(int expiry_date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(new Date().getTime());
        calendar.add(Calendar.MINUTE, expiry_date);
        return new Date(calendar.getTime().getTime());
    }

}
