package com.company;

public class Dish implements Item{
    private final double cost;
    private final String name;
    private final String description;

    public Dish(double cost, String name, String description) {
        this.cost = cost;
        this.name = name;
        this.description = description;
    }

    public Dish(String name, String description) {
        this.name = name;
        this.description = description;
        cost = 0.0;
    }

    @Override
    public double getCost() {
        return cost;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
