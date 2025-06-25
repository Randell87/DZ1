package org.skypro.skyshop.search;

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

    private int countSubstringOccurrences(String text, String substring) {
        if (text == null || substring == null || substring.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;
        int subLength = substring.length();

        while (true) {
            index = text.indexOf(substring, index);
            if (index == -1) break;
            count++;
            index += subLength;
        }

        return count;
    }

    public Searchable findMostRelevant(String search) throws BestResultNotFound{
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковая строка не может быть пустой или null");
        }

        Searchable mostRelevant = null;
        int maxCount = 0;

        for (int i = 0; i < items.length; i++) {
            if(items[i] == null) continue;

            String searchTerm = items[i].searchTerm();
            int count = countSubstringOccurrences(searchTerm, search);

            if(count > maxCount) {
                maxCount = count;
                mostRelevant = items[i];
            }
        }

        if (mostRelevant == null) {
            throw new BestResultNotFound("Не найдено подходящих результатов для запроса: \"" + search + "\"");
        }

        return mostRelevant;
    }
}
