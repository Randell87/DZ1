package org.skypro.skyshop.product;

public abstract class Product {
    private String name;
    //private int price;

    public Product(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int getPrice();

    public void setName(String name) {
        this.name = name;
    }

    public abstract String toString();

    public abstract boolean isSpecial();
}



