package org.skypro.skyshop;

import org.skypro.skyshop.article.Article;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;
import org.skypro.skyshop.search.BestResultNotFound;
import org.skypro.skyshop.search.SearchEngine;
import org.skypro.skyshop.search.Searchable;

import java.util.List;
import java.util.Map;

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

        // Вывод содержимого
        basket.printBasket();

        // Получение стоимости
        System.out.println("Общая стоимость: " + basket.getTotalPrice());

        // Поиск товаров
        System.out.println("Товар 'Водка' есть в корзине? " + basket.containsProductByName("Водка"));
        System.out.println("Товар 'Апельсин' есть в корзине? " + basket.containsProductByName("Апельсин"));

        //удаление товара с именем "Водка"
        String existingName = "Водка";
        List<Product> removed = basket.removeProductByName(existingName);

        System.out.println("\n=== Удаленные товары ===");
        if (removed.isEmpty()) {
            System.out.println("Список удаленных товаров пуст");
        } else {
            for (Product p : removed) {
                System.out.println(p.toString());
            }
        }

        System.out.println("\n=== Содержимое корзины после удаления \"" + existingName + "\" ===");
        basket.printBasket();

        //Попытка удаления не существующего товара
        String noExistingName = "Уран235";
        List<Product> removedNoExist = basket.removeProductByName(noExistingName);

        System.out.println("\n=== Результат удаления \"" + noExistingName + "\" ===");
        if (removedNoExist.isEmpty()) {
            System.out.println("Список пуст");
        }

        System.out.println("\n=== Содержимое корзины после попытки удаления \"" + noExistingName + "\" ===");
        basket.printBasket();

        // Очистка корзины
        basket.clearBasket();

        // Печать пустой корзины
        System.out.println("\nПосле очистки:");
        basket.printBasket();
        System.out.println("Общая стоимость: " + basket.getTotalPrice());
        System.out.println("Товар 'Хлеб' есть в корзине? " + basket.containsProductByName("Хлеб"));

        Article article1 = new Article("Книга", "Лучшие советы по выбору книг.");
        Article article2 = new Article("Уход за футболками", "Советы по уходу за одеждой.");
        Article article3 = new Article("Война и Мир", "О том, как шла война 1812.");

        // Создаем и наполняем SearchEngine
        SearchEngine engine = new SearchEngine();

        // Получаем все товары из корзины
        Product[] allProducts = basket.getAllProducts();

        // Добавляем их в SearchEngine
        for (Product product : allProducts) {
            engine.add(product);
        }

        // так как корзина пустая добавляем вновь созданные продукты

        System.out.println("Наполняем движок поиска... ");
        engine.add(new SimpleProduct("Хлеб", 35));
        engine.add(new SimpleProduct("Молоко", 70));
        engine.add(new SimpleProduct("Молочный коктейль", 120));
        engine.add(new SimpleProduct("Картофель", 25));
        engine.add(new SimpleProduct("Молочный шоколад", 80));
        engine.add(article1);
        engine.add(article2);
        engine.add(article3);

        // проверка неправильных полей
        System.out.println("=== Демонстрация проверок данных ===");

        try {
            // Неверное имя
            new SimpleProduct("", 100);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            // Цена не может быть меньше или равна нулю
            new SimpleProduct("Обогащенный уран", 0);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            // Базовая цена должна быть больше 0
            new DiscountedProduct("Скидочный товар", 0, 50);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            // Скидка вне диапазона 0-100
            new DiscountedProduct("Скидочный товар", 1000, 101);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            // Корректный объект — ошибок не будет
            new DiscountedProduct("Корректный товар", 1000, 20);
            System.out.println("Корректный товар успешно создан");
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        try {
            // Поиск с существующим результатом
            System.out.println("=== Тестовый поиск: \"Война\" ===");
            Searchable result = engine.findMostRelevant("Война");
            System.out.println("Найден результат: " + result.getStringRepresentation());

            // Поиск с НЕсуществующим результатом
            System.out.println("\n=== Тестовый поиск: \"плутоний\" ===");
            result = engine.findMostRelevant("плутоний");
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        // Выполняем поиск

        String query = "мол";
        Map<String, Searchable> results = engine.search(query);

        System.out.println("Результаты поиска по запросу \"" + query + "\":");
        if (results.isEmpty()) {
            System.out.println("Ничего не найдено.");
        } else {
            for (Map.Entry<String, Searchable> entry : results.entrySet()) {
                Searchable item = entry.getValue();
                System.out.println("- " + entry.getKey() + " (" + item.typeOfContent() + ")");
            }
        }

        try {
            Searchable bestMatch = engine.findMostRelevant("мол");
            System.out.println("\nНаиболее релевантный результат: " + bestMatch.searchTerm());
        } catch (BestResultNotFound e) {
            System.out.println(e.getMessage());
        }

    }

    private static void printResults(Searchable[] results) {
        for (Searchable item : results) {
            if (item != null) {
                System.out.println(item.getStringRepresentation());
            }
        }
    }


}