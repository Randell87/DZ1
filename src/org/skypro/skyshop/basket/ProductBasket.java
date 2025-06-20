package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Random;

public class ProductBasket {
    private static final int maxProducts = 5;
    private final Product[] products = new Product[maxProducts];
    private int count = 0; // счетчик продуктов в корзине

    private final Random random = new Random();
    private final String[] NAMES = {"Картофель", "Лук","Пиво","Свекла","Рыба"};

    public Product initProduct() {
            String name = NAMES[random.nextInt(NAMES.length)];
        return new SimpleProduct(name, random.nextInt(10, 100));
    }

    //Добавление продукта в корзину
    public void addProduct(Product product){
        if(count >= maxProducts) {
            System.out.println("Невозможно добавить продукт");
        } else {
            products[count] = product;
            count++;
        }
    }
    
    //Общая стоимость корзины
    public int getTotalPrice() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total +=products[i].getPrice();
        }
        return total;
    }
    
    //Вывод содержимого корзины
    public void printBasket(){
        if (count == 0 ){
            System.out.println("В корзине пусто");
            return;
        }

        for (int i = 0; i < count; i++) {
            System.out.println(products[i].toString());
        }
        System.out.println("Итого: " + getTotalPrice());

        int countOfSpecialProducts = 0;
        for (int i = 0; i < count; i++) {
            if (products[i].isSpecial())
               countOfSpecialProducts++;
        }
        System.out.println("Специальных товаров: " + countOfSpecialProducts);
    }

    public Product[] getAllProducts() {
        // Создаем новый массив только с реально добавленными товарами
        Product[] result = new Product[count];
        for (int i = 0; i < count; i++) {
            result[i] = products[i];
        }
        return result;
    }

    public boolean containsProductByName(String name){
        for (int i = 0; i < count; i++) {
            if (products[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket(){
        for (int i = 0; i < count; i++) {
            products[i] = null;
        }
        count = 0;
    }


}
