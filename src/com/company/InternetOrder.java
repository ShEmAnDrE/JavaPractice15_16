package com.company;

public class InternetOrder {
    private CloseLinkedListForInternetOrder items;

    public InternetOrder(Item[] items) {
        this.items.addAll(items);
    }

    public InternetOrder() {
        items = new CloseLinkedListForInternetOrder();
    }

    public boolean add(Item item) {
        return items.add(item);
    }

    public boolean remove(String itemName) {
        return (items.remove(items.lastIndexOfItemName(itemName))) != null;
    }

    public int removeAll(String itemName) {
        int count = 0;
        while ((items.remove(items.lastIndexOfItemName(itemName))) != null) {
            count ++;
        }
        return count;
    }

    public int getQuantityOfItems() {
        return items.size();
    }

    public double getCostTotal() {
        return items.getCostTotal();
    }

    public int quantityOfItem(String itemName) {
        return items.quantityOfItem(itemName);
    }

    public String[] namesOfItemsInOrder() {
        String[] names = items.namesOfItems();
        String name = "";
        for (int i = 0; i < names.length; i++) {
            name = names[i];
            for (int j = i + 1; j < names.length; j++) {
                if (name.equals(names[j])) {
                    System.arraycopy(names, j + 1, names, j, names.length - 1 - j);
                }
            }
        }
        return names;
    }

    public Item[] getDescendingSortItems() {
        //отсортированы в обратном порядке
        Item[] sortItems = items.toArray();
        // ShellSort
        // Высчитываем промежуток между проверяемыми элементами
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
