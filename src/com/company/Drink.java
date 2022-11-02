package com.company;

public class Drink implements Item{
    private final double cost;
    private final String name;
    private final String description;

    public Drink(double price, String name, String description) {
        this.cost = price;
        this.name = name;
        this.description = description;
    }

    public Drink(String name, String description) {
        this.name = name;
        this.description = description;
        cost = 0.0;
    }

    public double getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
