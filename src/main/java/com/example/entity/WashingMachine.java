package com.example.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "washing_machine")
public class WashingMachine extends Product implements Products{

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int performance;

    @Column(name = "tanksize")
    private int tankSize;

    @Column(name = "number_seller")
    private String numberSeller;

    private String type = "Plumbing";

    private double price;

    private String name;

    private int number;

}
