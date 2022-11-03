package com.company;

public class Dish implements Item{
    private final double cost;
    private final String name;
    private final String description;

    public Dish(double cost, String name, String description) throws IllegalArgumentException {
        if (cost < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.cost = cost;
        if (name.length()==0 || description.length() == 0) {
            if (name.length() == 0 && description.length() != 0) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            } else if (name.length() != 0 && description.length() == 0) {
                throw new IllegalArgumentException("Описание не может быть пустым");
            } else {
                throw new IllegalArgumentException("Имя и описание не могут быть пустыми");
            }
        }
        this.name = name;
        this.description = description;
    }

    public Dish(String name, String description) throws IllegalArgumentException {
        if (name.length()==0 || description.length() == 0) {
            if (name.length() == 0 && description.length() != 0) {
                throw new IllegalArgumentException("Имя не может быть пустым");
            } else if (name.length() != 0 && description.length() == 0) {
                throw new IllegalArgumentException("Описание не может быть пустым");
            } else {
                throw new IllegalArgumentException("Имя и описание не могут быть пустыми");
            }
        }
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
