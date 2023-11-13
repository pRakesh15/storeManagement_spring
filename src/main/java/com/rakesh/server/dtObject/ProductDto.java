package com.rakesh.server.dtObject;

import lombok.Data;

import javax.persistence.*;

@Data
public class ProductDto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private int category_id;
    private double price;
    private String description;
    private String imageName;
}
