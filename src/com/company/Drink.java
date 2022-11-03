package com.company;

public class Drink implements Item{
    private final double cost;
    private final String name;
    private final String description;

    public Drink(double price, String name, String description) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Стоимость не может быть отрицательной");
        }
        this.cost = price;
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

    public Drink(String name, String description) throws IllegalArgumentException {
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
