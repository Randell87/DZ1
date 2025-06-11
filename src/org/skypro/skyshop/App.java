package org.skypro.skyshop;

import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class App {
    public static void main(String[] args) {

        ProductBasket basket = new ProductBasket();

        // Добавление 3 обычных продуктов
        for (int i = 0; i < 3; i++) {
            basket.addProduct(basket.initProduct());
        }

        //добавление 2 специальных продуктов
        basket.addProduct(new DiscountedProduct("Водка", 100, 20));
        basket.addProduct(new FixPriceProduct("Доширак"));

        // Попытка добавить шестой — должен вывести сообщение
        basket.addProduct(new SimpleProduct("Шоколад", 70));

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