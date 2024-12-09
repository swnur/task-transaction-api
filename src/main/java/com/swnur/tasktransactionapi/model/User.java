package com.swnur.tasktransactionapi.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@Table(name = "user", schema = "public")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "account")
    private BigInteger account;

    public User(String firstName, String lastName, BigInteger account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.account = account;
    }
}
