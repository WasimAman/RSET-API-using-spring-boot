package com.rest.api.restapi.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Books {
    private String name;
    private double price;
    private AuthorDetails author;

    public Books(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
