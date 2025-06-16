package org.skypro.skyshop;

public class SearchEngine {
    private final Searchable[] items;
    private int count;

    public SearchEngine(int capacity) {
        this.items = new Searchable[capacity];
        this.count = 0;
    }

    public void add(Searchable item) {
        if (count < items.length) {
            items[count++] = item;
        } else {
            System.out.println("Поисковый массив заполнен.");
        }
    }
}
