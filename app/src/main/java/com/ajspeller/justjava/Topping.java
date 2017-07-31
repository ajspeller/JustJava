package com.ajspeller.justjava;

/**
 * Created by ny2va on 7/30/2017.
 */

public class Topping {

    public static final Topping Toppings[] = {
            new Topping("Whipped Cream", 1),
            new Topping("Chocolate", 1.50),
            new Topping("Nutella", 0.75),
            new Topping("Nutmeg", 0.00),
            new Topping("Mint", 0.10),
            new Topping("Cinnamon", 0.25)
    };
    private String name;
    private double price;

    public Topping(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
