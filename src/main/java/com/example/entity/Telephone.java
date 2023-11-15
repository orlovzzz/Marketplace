package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "telephone")
public class Telephone implements Products{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int performance;

    @Column(name = "accsize")
    private int accSize;

    @Column(name = "number_seller")
    private String numberSeller;

    private String type = "Electronic";

    private double price;

    private String name;

    private int number;

}
