package com.djamware;

public class Product {
    public Long id;
    public String name;
    public Double price;

    public Product() {
        this.id = null;
        this.name = null;
        this.price = null;
    }

    public Product(Long id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
}
