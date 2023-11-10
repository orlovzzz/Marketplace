package com.example.DTO;

import com.example.entity.Products;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProdCount {

    private Products products;

    private int count;

}
