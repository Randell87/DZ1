package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.*;
import java.util.stream.Collectors;

public class ProductBasket {

    private final Map<String, List<Product>> products = new HashMap<>();

    private final Random random = new Random();
    private final String[] NAMES = {"Картофель", "Лук", "Пиво", "Свекла", "Рыба"};
    private static final int MIN_PRICE = 10;
    private static final int MAX_PRICE = 100;

    /**
     * Генерирует случайный продукт для тестирования.
     */
    public Product initProduct() {
        String name = NAMES[random.nextInt(NAMES.length)];
        return new SimpleProduct(name, random.nextInt(MIN_PRICE, MAX_PRICE + 1));
    }

    /**
     * Добавляет продукт в корзину. Имя нормализуется в нижний регистр для согласованности.
     */
    public void addProduct(Product product) {
        String key = product.getName().toLowerCase();
        products.computeIfAbsent(key, k -> new ArrayList<>()).add(product);
    }

    /**
     * Удаляет все продукты по имени (регистронезависимо).
     */
    public List<Product> removeProductByName(String name) {
        List<Product> removed = products.remove(name.toLowerCase());
        return removed == null ? Collections.emptyList() : removed;
    }

    /**
     * Возвращает общую стоимость всех товаров в корзине.
     */
    public int getTotalPrice() {
        return products.values().stream()
                .flatMap(List::stream)
                .mapToInt(Product::getPrice)
                .sum();
    }

    /**
     * Выводит содержимое корзины: список товаров, итоговую сумму и количество специальных товаров.
     */
    public void printBasket() {
        List<Product> allProducts = getAllProductsAsList();

        if (allProducts.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        allProducts.forEach(System.out::println);

        int total = allProducts.stream()
                .mapToInt(Product::getPrice)
                .sum();

        long specialCount = getSpecialCount();

        System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + specialCount);
    }

    /**
     * Возвращает количество специальных товаров.
     */
    private long getSpecialCount() {
        return products.values().stream()
                .flatMap(List::stream)
                .filter(Product::isSpecial)
                .count();
    }

    /**
     * Возвращает все товары в виде массива.
     */
    public Product[] getAllProducts() {
        return getAllProductsAsList().toArray(new Product[0]);
    }

    /**
     * Проверяет, есть ли товар с указанным именем в корзине.
     */
    public boolean containsProductByName(String name) {
        return products.containsKey(name.toLowerCase());
    }

    /**
     * Очищает корзину.
     */
    public void clearBasket() {
        products.clear();
    }

    /**
     * Возвращает все продукты в виде списка.
     */
    private List<Product> getAllProductsAsList() {
        return products.values().stream()
                .flatMap(List::stream)
                .toList(); // immutable list (Java 16+), или .collect(Collectors.toList()) для Java 8–15
    }
}