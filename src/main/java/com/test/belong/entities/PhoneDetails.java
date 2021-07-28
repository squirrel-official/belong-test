package com.test.belong.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name ="phone_details" )
@Getter @Setter
public class PhoneDetails {
    @Id
    private Long id;


//    @Column(name = "customer_id")
//    private String customerId;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "activated")
    private boolean activated;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private Customer customer;

}
