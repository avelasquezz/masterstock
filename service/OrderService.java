package service;

import repository.OrderRepository;
import model.Order;

import java.util.Random;

import javax.swing.table.DefaultTableModel;

public class OrderService {
    private OrderService() {}
    private static OrderService instance;

    public static OrderService getInstance() {
        if (instance == null) {
            instance = new OrderService();
        }
        return instance;
    }

    private OrderRepository orderRepository = OrderRepository.getInstance();

    public OrderRepository getOrderRepository() {
        return this.orderRepository;
    }

    public void updateTable(DefaultTableModel ordersTableModel) {
        ordersTableModel.setRowCount(0);
        
        for (Order order : orderRepository.getOrdersList()) {
            String[] tableRow = {
                String.valueOf(order.getId()),
                order.getOrderDate().toString(),
                order.getProduct().getName(),
                order.getSupplier().getName(),
                String.valueOf(order.getQuantity()),
                order.getState() == true ? "Received" : "Not received",
                order.getReceivedDate() == null ? "" : order.getReceivedDate().toString()
            };
            ordersTableModel.addRow(tableRow);
        }

        ordersTableModel.fireTableDataChanged();
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Order order : this.orderRepository.getOrdersList()) {
                findingNewId = findingNewId || order.getId() == newId;
            }
        }

        return newId;
    }
}
