package org.skypro.skyshop.search;

import java.util.*;
import java.util.stream.Collectors;

public class SearchEngine {
    private final Set<Searchable> items = new HashSet<>();

    public void add(Searchable item) {
        if (item == null) {
            throw new IllegalArgumentException("Нельзя добавить null в поисковую систему");
        }
        items.add(item);
    }

    public Set<Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return new TreeSet<>(getSearchableComparator());
        }

        String lowerCaseQuery = query.toLowerCase();

        return items.stream()
                .filter(item -> item.searchTerm().toLowerCase().contains(lowerCaseQuery))
                .collect(Collectors.toCollection(() -> new TreeSet<>(getSearchableComparator())));
    }

    private Comparator<Searchable> getSearchableComparator() {
        return (o1, o2) -> {
            int lengthCompare = Integer.compare(o2.getName().length(), o1.getName().length());
            if (lengthCompare != 0) {
                return lengthCompare; // сортировка по длине (от длинного к короткому)
            }
            return o1.getName().compareToIgnoreCase(o2.getName()); // при равной длине — по алфавиту
        };
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
