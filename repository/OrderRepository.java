package repository;

import java.util.List;
import java.util.ArrayList;

import model.Order;

public class OrderRepository {
    private OrderRepository() {}
    private static OrderRepository instance;

    public static OrderRepository getInstance() {
        if (instance == null) {
            instance = new OrderRepository();
        }
        return instance;
    }

    private List<Order> ordersList = new ArrayList<>();

    public Order searchOrderById(int id) {
        for (Order order : ordersList) {
            if (order.getId() == id) {
                return order;
            }
        }
        return null;
    }

    public void addOrder(Order orderToAdd) {
        ordersList.add(orderToAdd);
    }

    public void removeOrder(Order orderToRemove) {
        ordersList.remove(orderToRemove);
    }

    public void updateOrder(Order modifiedOrder) {
        int modifiedOrderId = modifiedOrder.getId();
        Order originalOrder = searchOrderById(modifiedOrderId);
        int originalOrderIndex = ordersList.indexOf(originalOrder);

        ordersList.set(originalOrderIndex, modifiedOrder);
    }

    public List<Order> getOrdersList() {
        return new ArrayList<>(ordersList);
    }
}
