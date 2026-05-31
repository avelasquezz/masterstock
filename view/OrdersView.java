package view;

import service.*;
import view.ordersDialogs.PlaceOrderDialog;
import view.ordersDialogs.ReceiveOrderDialog;

import javax.swing.*;
import javax.swing.table.JTableHeader;

import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OrdersView extends JFrame {
    private UserService userService = UserService.getInstance();
    private ProductService productService = ProductService.getInstance();
    private SupplierService supplierService = SupplierService.getInstance();
    private MovementService movementService = MovementService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    private NotificationService notificationService = NotificationService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    
    private JLabel ordersTableTitle;
    private DefaultTableModel ordersTableModel;
    private JTable ordersTable;
    private JButton backButton;
    private JButton PlaceOrderButton;
    private JButton receiveOrderButton;

    public OrdersView(String userAccesLevel, String welcomeMessage) {
        // Window config
        setTitle("MasterStock | Orders");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.ordersTableTitle = new JLabel("Orders");
        this.ordersTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.ordersTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"ID", "Order date", "Product", "Supplier", "Amount", "State", "Receipt date"};
        this.ordersTableModel = new DefaultTableModel(tableColumns, 0);
        this.ordersTable = new JTable(ordersTableModel);

        JTableHeader productsTableHeader = this.ordersTable.getTableHeader();
        productsTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
    
        tablePanel.add(this.ordersTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.ordersTable));
        tablePanel.add(Box.createRigidArea(new Dimension(0, 10)));

        // Create subpanel to organize buttons
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        this.backButton = new JButton("Back");
        this.backButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.backButton.setContentAreaFilled(true); 
    	this.backButton.setBorderPainted(false); 
    	this.backButton.setFocusPainted(false); 
        this.backButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.backButton.setForeground(Color.WHITE);
        this.backButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.PlaceOrderButton = new JButton("Place order");
        this.PlaceOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.PlaceOrderButton.setContentAreaFilled(true); 
    	this.PlaceOrderButton.setBorderPainted(false); 
    	this.PlaceOrderButton.setFocusPainted(false); 
        this.PlaceOrderButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.PlaceOrderButton.setForeground(Color.WHITE);
        this.PlaceOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.receiveOrderButton = new JButton("Receive order");
        this.receiveOrderButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.receiveOrderButton.setContentAreaFilled(true); 
    	this.receiveOrderButton.setBorderPainted(false); 
    	this.receiveOrderButton.setFocusPainted(false); 
        this.receiveOrderButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.receiveOrderButton.setForeground(Color.WHITE);
        this.receiveOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.PlaceOrderButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.receiveOrderButton);
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        OrdersView.this.orderService.updateTable((DefaultTableModel) ordersTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage);
                dashboardView.showWindow();
            }
        });

        PlaceOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlaceOrderDialog PlaceOrderDialog = new PlaceOrderDialog(OrdersView.this.ordersTable);
                PlaceOrderDialog.showDialog();
            }
        });

        receiveOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = OrdersView.this.ordersTable.getSelectedRow();

                if (selectedRow >= 0) {
                    ReceiveOrderDialog receiveOrderDialog = new ReceiveOrderDialog(OrdersView.this.ordersTable);
                    receiveOrderDialog.showDialog();
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}
