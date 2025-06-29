package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.*;

public class ProductBasket {

    private final Map<String, List<Product>> products = new HashMap<>();

    private final Random random = new Random();
    private final String[] NAMES = {"Картофель", "Лук", "Пиво", "Свекла", "Рыба"};
    private static final int MIN_PRICE = 10;
    private static final int MAX_PRICE = 100;

    public Product initProduct() {
        String name = NAMES[random.nextInt(NAMES.length)];
        return new SimpleProduct(name, random.nextInt(MIN_PRICE, MAX_PRICE + 1));
    }

    //Добавление продукта в корзину
    public void addProduct(Product product){//
        products.computeIfAbsent(product.getName(), k -> new ArrayList<>()).add(product);

    }

    // Удаление продуктов по имени
    public List<Product> removeProductByName(String name) {
        List<Product> removed = products.remove(name.toLowerCase());
        return removed == null ? Collections.emptyList() : removed;
    }

    // Общая стоимость корзины
    public int getTotalPrice() {
        int total = 0;
        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                total += product.getPrice();
            }
        }
        return total;
    }

    // Вывод содержимого корзины
    public void printBasket() {
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        int total = 0;
        int specialCount = 0;

        for (List<Product> productList : products.values()) {
            for (Product product : productList) {
                System.out.println(product.toString());
                total += product.getPrice();
                if (product.isSpecial()) {
                    specialCount++;
                }
            }
        }

        System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + specialCount);
    }

    // Получить все товары в виде массива
    public Product[] getAllProducts() {
        List<Product> all = new ArrayList<>();
        for (List<Product> productList : products.values()) {
            all.addAll(productList);
        }
        return all.toArray(new Product[0]);
    }

    // Проверка наличия товара по имени
    public boolean containsProductByName(String name) {
        return products.containsKey(name.toLowerCase());
    }

    // Очистить корзину
    public void clearBasket() {
        products.clear();
    }


}
