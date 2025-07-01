package org.skypro.skyshop.article;

import org.skypro.skyshop.search.Searchable;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Article article)) return false;
        return Objects.equals(name, article.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }
}
