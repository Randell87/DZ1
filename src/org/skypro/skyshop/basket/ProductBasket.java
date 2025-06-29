package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.*;

public class ProductBasket {
    //private static final int maxProducts = 5;
    //private final Product[] products = new Product[maxProducts];
    List<Product> products = new LinkedList<>();
    //private int count = 0; // счетчик продуктов в корзине

    private final Random random = new Random();
    private final String[] NAMES = {"Картофель", "Лук","Пиво","Свекла","Рыба"};

    public Product initProduct() {
            String name = NAMES[random.nextInt(NAMES.length)];
        return new SimpleProduct(name, random.nextInt(10, 100));
    }

    //Добавление продукта в корзину
    public void addProduct(Product product){//
            products.add(product);
            //count++;
    }

    public List<Product> removeProductByName (String name) {
        Iterator<Product> iterator = products.iterator(); //создаем итератор для корзины продуктов
        List<Product> removedProducts = new LinkedList<>(); //создаем список удаленных продуктов
        while (iterator.hasNext()) { //цикл по итератору, пока есть следующий
            Product product = iterator.next(); // при помощи итератора достаем каждый продукт
            if (product.getName().equals(name)) { //сравниваем имя этого продукта с запрашиваемым
                removedProducts.add(product); //добавляем его в список удаленных продуктов
                iterator.remove(); //удалям его из корзины
            }
        }
        return removedProducts; // возвращаем список удаленных продуктов
    }
    
    //Общая стоимость корзины
    public int getTotalPrice() {
        int total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }
    
    //Вывод содержимого корзины
    public void printBasket(){
        if (products.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        for (Product product : products) {
            System.out.println(product.toString());
        }
        System.out.println("Итого: " + getTotalPrice());

        int countOfSpecialProducts = 0;
        for (Product product : products) {
            if (product.isSpecial())
                countOfSpecialProducts++;
        }
        System.out.println("Специальных товаров: " + countOfSpecialProducts);
    }

    public Product[] getAllProducts() {
        // Создаем новый массив только с реально добавленными товарами
        Product[] result = new Product[products.size()];
        for (int i = 0; i < products.size(); i++) {
            result[i] = products.get(i);
        }
        return result;
    }

    public boolean containsProductByName(String name){
        for (Product product : products) {
            if (product.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket(){
        products.clear();
    }


}
