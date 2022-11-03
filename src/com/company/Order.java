package com.company;

public interface Order {
    public boolean add(Item item);
    public boolean remove(String itemName);
    public int removeAll(String itemName);
    public int getQuantityOfItems();
    public Item[] getItems();
    public double getCostTotal();
    public int quantityOfItem(String itemName);
    public String[] namesOfItemsInOrder();
    public Item[] getDescendingSortItems();
}
