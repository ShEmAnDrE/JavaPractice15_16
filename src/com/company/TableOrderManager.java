package com.company;

import java.security.PublicKey;

public class TableOrderManager {
    private Order[] orders;

    public TableOrderManager(Order[] orders) {
        this.orders = orders;
    }

    public TableOrderManager(int length) {
        orders = new Order[length];
    }

    public TableOrderManager() {
        orders = new Order[10];
    }

    public void add(Order order, int tableNumber) {
        if (tableNumber >= orders.length) {
            System.out.println("Все занято");
        } else if (orders[tableNumber] != null) {
            System.out.println("Этот столик занят");
        } else {
            orders[tableNumber] = order;
        }
    }

    public Order getOrder(int tableNumber) {
        if (orders[tableNumber] == null) {
            System.out.println("Нет такого заказа");
        }
        return orders[tableNumber];
    }

    public void addItem(Item item, int tableNumber) {
        if (tableNumber >= orders.length) {
            System.out.println("Неверный номер заказа");
        }
        if (orders[tableNumber] == null) {
            System.out.println("Нет такого заказа в списке");
        }
        orders[tableNumber].add(item);
    }

    public void removeOrder(int tableNumber) {
        if (orders.length <= tableNumber) {
            System.out.println("Нет такого заказа");
        } else {
            System.arraycopy(orders, tableNumber + 1, orders, tableNumber, orders.length - tableNumber);
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

    public double ordersCostSummary() {
        double sum = 0.0;
        for (Order order : orders) {
            sum += order.costTotal();
        }
        return sum;
    }

    public int itemQuantity(String itemName) {
        int count = 0;
        for (Order order : orders) {
            count += order.itemQuantity(itemName);
        }
        return count;
    }
}
