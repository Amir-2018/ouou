package com.ouou.models;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id ;
    @Column(name = "name")
    private String name ;
    @Column(name = "description")
    private String description ;
    @Column(name = "price")
    private String price ;
    @Column(name = "image", columnDefinition = "bytea")
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private Categorie categorie;


}
