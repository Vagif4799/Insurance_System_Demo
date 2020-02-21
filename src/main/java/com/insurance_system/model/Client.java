package com.insurance_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "clients")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "client_id")
    private Long id;

    @Column
    @NotNull
    @Size(min = 1, message = "This field can't be empty.")
    private String name;

    @Column
    private String surname;

    @Column(unique = true)
    @NotNull
    @Size(min = 1, message = "This field can't be empty")
    @Email
    private String email;

    @Column(name = "client_FIN")
    @Size(min = 7, max = 7)
    @NotBlank(message = "This field can not be blank")
    private String FIN;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "r_clients_insurances",
            joinColumns = @JoinColumn(name = "client_id", referencedColumnName = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id", referencedColumnName = "insurance_id")
    )
    private List<Insurance> insurances;



}

