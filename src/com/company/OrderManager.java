package com.company;

import java.util.HashMap;

public class OrderManager {
    private Order[] orders;
    private HashMap<String, Order> internetOrders;

    public OrderManager(Order[] orders) {
        this.orders = orders;
    }

    public OrderManager(int length) {
        orders = new Order[length];
    }

    public OrderManager() {
        orders = new Order[10];
    }

    public void add(Order order, int tableNumber) throws OrderAlreadyAddedException, IllegalTableNumberException {
        if (tableNumber >= orders.length) {
            throw new IllegalTableNumberException("Нет такого столика (" + tableNumber + ")");
        } else if (orders[tableNumber] != null) {
            throw new OrderAlreadyAddedException("Этот столик занят");
        } else {
            orders[tableNumber] = order;
        }
    }

    public void add(Order order, String address) throws OrderAlreadyAddedException {
        if (internetOrders.containsKey(address)) {
            throw new OrderAlreadyAddedException("Такой адрес уже есть");
        } else {
            internetOrders.put(address, order);
        }
    }

    public Order getOrder(int tableNumber) throws IllegalArgumentException {
        if (orders[tableNumber] == null) {
            throw new IllegalArgumentException("Нет такого заказа");
        }
        return orders[tableNumber];
    }

    public Order getOrder(String address) throws IllegalArgumentException {
        if (internetOrders.size() == 0) {
            throw new IllegalArgumentException("Заказов нет");
        }
        if (internetOrders.get(address) == null) {
            throw new IllegalArgumentException("Нет такого заказа");
        }
        return internetOrders.get(address);
    }

    public void addItem(Item item, int tableNumber) throws IllegalTableNumberException{
        if (tableNumber >= orders.length) {
            throw new IllegalTableNumberException("Нет такого столика (" + tableNumber + ")");
        }
        if (orders[tableNumber] == null) {
            throw new IllegalTableNumberException("Нет такого столика (" + tableNumber + ") в списке");
        }
        orders[tableNumber].add(item);
    }

    public void addItem(Item item, String address) throws IllegalTableNumberException{
        if (!internetOrders.containsKey(address)) {
            throw new IllegalTableNumberException("Нет такого заказа (" + address + ") в списке");
        }
        internetOrders.get(address).add(item); // проверить на изменяемость
    }

    public void removeOrder(int tableNumber) throws IllegalTableNumberException{
        if (orders.length <= tableNumber) {
            throw new IllegalTableNumberException("Нет такого столика (" + tableNumber + ")");
        } else {
            System.arraycopy(orders, tableNumber + 1, orders, tableNumber, orders.length - tableNumber);
        }
    }

    public Order removeOrder(String address) throws IllegalTableNumberException{
        if (internetOrders.size() == 0) {
            throw new IllegalTableNumberException("Нет такого заказа (" + address + ") в списке");
        }
        if (internetOrders.containsKey(address)) {
            return internetOrders.remove(address);
        } else {
            throw new IllegalTableNumberException("Нет такого заказа (" + address + ") в списке");
        }
    }

    public int freeTableNumber() {
        for (int i = 0; i < orders.length; i++) {
            if (orders[i] == null) {
                return i;
            }
        }
        return -1;
    }

    public int[] freeTableNumbers() {
        int[] nums = new int[0];
        for(int i = 0; i < orders.length; i++) {
            if(orders[i] == null) {
                int[]tmp = new int[nums.length+1];
                System.arraycopy(nums, 0, tmp, 0, nums.length);
                tmp[tmp.length-1] = i;
                nums = tmp;
            }
        }
        if(nums.length > 0) {
            return nums;
        }
        return null;
    }

    public Order[] getOrders() {
        return orders;
    }

    public Order[] getInternetOrders() {
        return (Order[]) internetOrders.values().toArray();
    }

    public double ordersCostSummary() {
        double sum = 0.0;
        for (Order order : orders) {
            sum += order.getCostTotal();
        }
        return sum;
    }

    public double internetOrdersCostSummary() {
        double sum = 0.0;
        for (Order internetOrder : internetOrders.values()) {
            sum += internetOrder.getCostTotal();
        }
        return sum;
    }

    public int itemQuantityInOrders(String itemName) {
        int count = 0;
        for (Order order : orders) {
            count += order.quantityOfItem(itemName);
        }
        return count;
    }

    public int itemQuantityInInternetOrders(String itemName) {
        int count = 0;
        for (Order internetOrder : internetOrders.values()) {
            count += internetOrder.quantityOfItem(itemName);
        }
        return count;
    }
}
