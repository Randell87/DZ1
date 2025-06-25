package org.skypro.skyshop.product;

public class DiscountedProduct extends Product{

    private int basicPrice;
    private final int discont;

    public DiscountedProduct(String name, int basicPrice, int discont) {
        super(name);
        if( basicPrice <= 0 ){
            throw new IllegalArgumentException("Цена должна быть строго больше 0");
        }
        this.basicPrice = basicPrice;
        if( discont < 0 || discont > 100){
            throw new IllegalArgumentException("Скидка должна быть в диапазоне от 0 до 100 %");
        }
        this.discont = discont;
    }

    @Override
    public int getPrice() {
        if (discont < 0 || discont > 100) {
            return basicPrice; // если скидка некорректна — вернуть базовую цену
        }
        return (int) (basicPrice * (100 - discont) / 100.0); // вещественная арифметика
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discont + "%)";
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
