package com.example.entity;

import com.google.gson.annotations.SerializedName;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
//@Getter
public class Product {

    @Id
    @SerializedName("product_id")
    private int id;

}
