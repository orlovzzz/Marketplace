package com.example.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book implements Products{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private int id;

    @Column(name = "number_seller")
    private String numberSeller;

    private String type = "Book";

    private double price;

    private String name;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    private int number;
}