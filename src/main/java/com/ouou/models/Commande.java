package com.ouou.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "name")
    private String name ;
    @Column(name = "date")
    private String date ;

    @Column(name = "quantity")
    private float quantity;

    @Column(name = "total")
    private String total;

    @Column(name = "status")
    private String status ;

    @Column(name = "delivred")
    private String delivred ;

    @Column(name = "clientTel")
    private String clientTel ;

    @Column(name = "nameClient")
    private String nameClient;

    @Column(name = "lastnameClient")
    private String lastnameClient;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
