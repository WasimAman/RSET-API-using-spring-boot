package com.rest.api.restapi.DTOs;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Authors {
    private String name;
    private String country;
    private List<BookDetails> books;

    public Authors(String name, String country) {
        this.name = name;
        this.country = country;
    }    
}
