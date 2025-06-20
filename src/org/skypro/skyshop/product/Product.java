package org.skypro.skyshop.product;

import org.skypro.skyshop.search.Searchable;

public abstract class Product implements Searchable {
    private String name;
    //private int price;

    public Product(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Название продукта не может быть null, пустым или состоять только из пробелов");
        }
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

    @Override
    public String searchTerm() {
        return this.getName();
    }

    @Override
    public String typeOfContent() {
        return "PRODUCT";
    }
}



