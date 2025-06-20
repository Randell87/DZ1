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

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int index = 0;

        for (Searchable item : items) {
            if (item == null) continue;
            if (item.searchTerm().toLowerCase().contains(query.toLowerCase())) {
                result[index++] = item;
                if (index == result.length) break;
            }
        }

        return result;
    }
}
