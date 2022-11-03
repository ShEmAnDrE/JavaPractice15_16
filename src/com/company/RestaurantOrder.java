package com.company;

public class RestaurantOrder implements Order{
    private int size;
    private Item[] dishesAndDrinks;

    public RestaurantOrder(int length) {
        dishesAndDrinks = new Item[length];
    }

    public RestaurantOrder() {
        dishesAndDrinks = new Item[10];
    }

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

    @Override
    public int getQuantityOfItems() {
        return size;
    }

    public Item[] getItems() {
        return dishesAndDrinks;
    }

    @Override
    public double getCostTotal() {
        double sumCost = 0.0;
        for (Item item : dishesAndDrinks) {
            sumCost += item.getCost();
        }
        return sumCost;
    }

    @Override
    public int quantityOfItem(String itemName) {
        int count = 0;
        for (Item item : dishesAndDrinks) {
            if (item.getName().equals(itemName)) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String[] namesOfItemsInOrder() {
        String[] names = new String[dishesAndDrinks.length];
        for (int i = 0; i < dishesAndDrinks.length; i++) {
            names[i] = dishesAndDrinks[i].getName();
        }
        return names;
    }

    @Override
    public Item[] getDescendingSortItems() {
        // ShellSort
        // Высчитываем промежуток между проверяемыми элементами
        Item[] sortItems = new Item[dishesAndDrinks.length];
        System.arraycopy(dishesAndDrinks, 0, sortItems, 0, dishesAndDrinks.length);
        int gap = sortItems.length / 2;
        Item temp;
// Пока разница между элементами есть
        while (gap >= 1) {
            for (int right = 0; right < sortItems.length; right++) {
                // Смещаем правый указатель, пока не сможем найти такой, что
                // между ним и элементом до него не будет нужного промежутка
                for (int c = right - gap; c >= 0; c -= gap) {
                    if (sortItems[c].getCost() < sortItems[c + gap].getCost()) {
                        temp = sortItems[c + gap];
                        sortItems[c + gap] = sortItems[c];
                        sortItems[c] = temp;
                    }
                }
            }
            // Пересчитываем разрыв
            gap = gap / 2;
        }
        return sortItems;
    }
}
