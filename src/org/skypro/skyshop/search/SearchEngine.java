package org.skypro.skyshop.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final List<Searchable> items = new ArrayList<>();

    public void add(Searchable item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null в поисковую систему");
        }
        items.add(item);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> result = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        if (query == null || query.isBlank()) {
            return result; // Возвращаем пустой список, если запрос пустой
        }

        String lowerCaseQuery = query.toLowerCase();

        for (Searchable item : items) {
            if (item.searchTerm().toLowerCase().contains(lowerCaseQuery)) {
                result.put(item.getName(), item);
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

    public Searchable findMostRelevant(String search) throws BestResultNotFound {
        if (search == null || search.isBlank()) {
            throw new IllegalArgumentException("Поисковая строка не может быть пустой или null");
        }

        Searchable mostRelevant = null;
        int maxCount = 0;

        for (Searchable item : items) {
            int count = countSubstringOccurrences(item.searchTerm().toLowerCase(), search.toLowerCase());

            if (count > maxCount) {
                maxCount = count;
                mostRelevant = item;
            }
        }

        if (mostRelevant == null) {
            throw new BestResultNotFound("Не найдено подходящих результатов для запроса: \"" + search + "\"");
        }

        return mostRelevant;
    }
}
