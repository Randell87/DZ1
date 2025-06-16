package org.skypro.skyshop.article;

import org.skypro.skyshop.Searchable;

public final class Article implements Searchable {
    private final String name;
    private final String text;

    public Article(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public String getText() {
        return text;
    }


    @Override
    public String toString() {
        return getName() + "\n" + getText();
    }

    @Override
    public String searchTerm() {
        return getName() + " " + getText();
    }

    @Override
    public String typeOfContent() {
        return "PRODUCT";
    }
}
