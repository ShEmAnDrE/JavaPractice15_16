package com.company;

public class Order {
    private int size;
    private Item[] dishesAndDrinks;

    public boolean add(Item item) {
        if (size < dishesAndDrinks.length) {
            dishesAndDrinks[size] = item;
            size++;
            return true;
        }
        return false;
    }

    public boolean remove(String itemName) {
        if (size <= 0) {
            return false;
        }
        for (int i = 0; i < dishesAndDrinks.length; i++) {
            if (dishesAndDrinks[i].getName().equals(itemName)) {
                System.arraycopy(dishesAndDrinks, i + 1, dishesAndDrinks, i, dishesAndDrinks.length - i);
                size--;
                return true;
            }
        }
        return false;
    }

    public int removeAll(String itemName) {
        int count = 0;
        while (remove(itemName)) {
            count++;
        }
        return count;
    }

    public int itemsQuantity() {
        return size;
    }

    public int itemQuantity(String itemName) {
        int count = 0;
        for (Item item : dishesAndDrinks) {
            if (item.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    public Item[] getItems() {
        return dishesAndDrinks;
    }

    public double costTotal() {
        double sumCost = 0.0;
        for (Item item : dishesAndDrinks) {
            sumCost += item.getCost();
        }
        return sumCost;
    }

    public String[] itemsNames() {
        String[] names = new String[dishesAndDrinks.length];
        for (int i = 0; i < dishesAndDrinks.length; i++) {
            names[i] = dishesAndDrinks[i].getName();
        }
        return names;
    }

    public Item[] sortedItemsByCostDesc() {
        sortedByCost();
        return dishesAndDrinks;
    }

    public void sortedByCost() {
        // ShellSort
        // Высчитываем промежуток между проверяемыми элементами
        int gap = dishesAndDrinks.length / 2;
        Item temp;
// Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < dishesAndDrinks.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (dishesAndDrinks[c].getCost() > dishesAndDrinks[c + gap].getCost()) {
                        temp = dishesAndDrinks[c + gap];
                        dishesAndDrinks[c + gap] = dishesAndDrinks[c];
                        dishesAndDrinks[c] = temp;
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
    }

    public Order(int length) {
        dishesAndDrinks = new Item[length];
    }

    public Order() {
        dishesAndDrinks = new Item[10];
    }
}
