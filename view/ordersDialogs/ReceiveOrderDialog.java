package view.ordersDialogs;

import model.Inventory;
import model.Movement;
import model.Order;
import model.Product;
import model.Supplier;
import service.MovementService;
import service.OrderService;
import service.InventoryService;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class ReceiveOrderDialog extends JDialog {
    private OrderService orderService = OrderService.getInstance();
    private MovementService movementService = MovementService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    
    private JLabel unitPriceTextFieldLabel;
    private JTextField unitPriceTextField;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ReceiveOrderDialog(JTable ordersTable) {
        // Dialog config
        setTitle("Receive order");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.unitPriceTextFieldLabel = new JLabel("Unit purchase price");
        this.unitPriceTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.unitPriceTextField = new JTextField();
        this.unitPriceTextField.setFont(new Font("Arial", Font.PLAIN, 18));
        this.unitPriceTextField.setMaximumSize(new Dimension(200, 40));
        this.unitPriceTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.acceptButton = new JButton("Accept");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 16));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        dialogPanel.add(Box.createVerticalGlue());
        
        dialogPanel.add(this.unitPriceTextFieldLabel);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.unitPriceTextField);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        dialogPanel.add(this.acceptButton);
        dialogPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        dialogPanel.add(this.errorMessageLabel);
        
        dialogPanel.add(Box.createVerticalGlue());

        add(dialogPanel);

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int orderId = Integer.parseInt((String) ordersTable.getValueAt(ordersTable.getSelectedRow(), 0));
                    Order originalOrder = ReceiveOrderDialog.this.orderService.getOrderRepository().searchOrderById(orderId);

                    int newOrderId = originalOrder.getId();
                    LocalDate newOrderDate = originalOrder.getOrderDate();
                    Product newOrderProduct = originalOrder.getProduct();
                    Supplier newOrderSupplier = originalOrder.getSupplier();
                    int newOrderQuantity = originalOrder.getQuantity();
                    boolean newOrderSate = true;
                    LocalDate newOrderReceivedDate = LocalDate.now();

                    Order newOrder = new Order(newOrderId, newOrderDate, newOrderProduct, newOrderSupplier, newOrderQuantity, newOrderSate, newOrderReceivedDate);

                    ReceiveOrderDialog.this.orderService.getOrderRepository().updateOrder(newOrder);
                    ReceiveOrderDialog.this.orderService.updateTable((DefaultTableModel) ordersTable.getModel());

                    int newMovementId = ReceiveOrderDialog.this.movementService.generateId();
                    LocalDate newMovementDate = LocalDate.now();
                    String newMovementType = "Purchase";
                    int newMovementQuantity = newOrder.getQuantity();
                    double newMovementUnitPrice = Double.valueOf(ReceiveOrderDialog.this.unitPriceTextField.getText());
                    String newMovementDescription = "Receiving supplier order.";
                    Product newMovementProduct = newOrder.getProduct();

                    Movement newMovement = new Movement(newMovementId, newMovementDate, newMovementType, newMovementQuantity, newMovementUnitPrice, newMovementDescription, newMovementProduct);
                    ReceiveOrderDialog.this.movementService.getMovementRepository().addMovement(newMovement);

                    Inventory originalInventory = ReceiveOrderDialog.this.inventoryService.getInventoryRepository().searchInventoryByProduct(newMovementProduct); 
                    int newInventoryBalance = ReceiveOrderDialog.this.inventoryService.calculateBalance(newMovementProduct);

                    double newInventoryUnitPrice = ReceiveOrderDialog.this.inventoryService.calculateUnitPrice(newMovementProduct);
                    double newInventoryTotalPrice = ReceiveOrderDialog.this.inventoryService.calculateTotalPrice(newMovementProduct);
                    Inventory newInventory = new Inventory(newMovementProduct, newInventoryBalance, newInventoryUnitPrice, newInventoryTotalPrice, originalInventory.getMinStock(), originalInventory.getMaxStock());
                    inventoryService.getInventoryRepository().updateInventory(newInventory);
                    
                    dispose();
                } catch (NumberFormatException ex) {
					ReceiveOrderDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
