package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.Product;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {
        Product product1 = new Product("Хлеб", 50);
        Product product2 = new Product("Молоко", 80);
        Product product3 = new Product("Яйца", 120);
        Product product4 = new Product("Сыр", 150);
        Product product5 = new Product("Колбаса", 200);

        ProductBasket basket = new ProductBasket();

        // Добавление продуктов
        basket.addProduct(product1);
        basket.addProduct(product2);
        basket.addProduct(product3);
        basket.addProduct(product4);
        basket.addProduct(product5);

        // Попытка добавить шестой — должен вывести сообщение
        basket.addProduct(new Product("Шоколад", 70));

        // Вывод содержимого
        basket.printBasket();

        // Получение стоимости
        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        // Поиск товаров
        System.out.println("Товар 'Хлеб' есть в корзине? " + basket.containsProductByName("Хлеб"));
        System.out.println("Товар 'Апельсин' есть в корзине? " + basket.containsProductByName("Апельсин"));

        // Очистка корзины
        basket.clearBasket();

        // Печать пустой корзины
        System.out.println("\nПосле очистки:");
        basket.printBasket();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Товар 'Хлеб' есть в корзине? " + basket.containsProductByName("Хлеб"));

    }
}