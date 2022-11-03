package com.company;
public class Main {
    public static void main(String[] args) {
        try {
            Item cocaCola = new Drink(100.0,"Coca-Cola", "The most famous drink in the world");
            Item burger = new Dish(200.0, "Burger", "Fast and delicious");

            Order order = new RestaurantOrder(3);
            order.add(cocaCola);
            order.add(burger);
            order.add(burger);

            Order internetOrder = new InternetOrder();
            internetOrder.add(cocaCola);
            internetOrder.add(cocaCola);
            internetOrder.add(burger);

            OrderManager orderManager = new OrderManager(1);
            orderManager.add(order, 0);
            orderManager.add(internetOrder, "Заказ №1");

            System.out.println(orderManager.getOrder(0).namesOfItemsInOrder()[0]);
            System.out.println(orderManager.getOrder("Заказ №1").namesOfItemsInOrder()[0]);

            System.out.println(orderManager.ordersCostSummary());
            System.out.println(orderManager.internetOrdersCostSummary());

            System.out.println(orderManager.getOrder("Заказ №1").getItems().length);
            orderManager.addItem(cocaCola, "Заказ №1");
            System.out.println(orderManager.getOrder("Заказ №1").getItems().length);
        } catch (IllegalArgumentException | IllegalTableNumberException | OrderAlreadyAddedException e) {
            e.printStackTrace();
        }


    }
}