package view;

import service.*;

import javax.swing.*;

import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardView extends JFrame {
    private UserService userService = UserService.getInstance();
    private ProductService productService = ProductService.getInstance();
    private SupplierService supplierService = SupplierService.getInstance();
    private MovementService movementService = MovementService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    private NotificationService notificationService = NotificationService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    
    private JLabel welcomeMessageLabel;
    private JButton seeInventoryButton;
    private JButton notificationsButton;
    private JButton ordersButton;
    private JButton manageUsersButton;
    private JButton manageProductsButton;
    private JButton manageSuppliersButton;
    private JButton logOutButton;

    public DashboardView(String userAccesLevel, String welcomeMessage) {
        // Window config
        setTitle("MasterStock | Dashboard");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BoxLayout(homePanel, BoxLayout.Y_AXIS));

        this.welcomeMessageLabel = new JLabel(welcomeMessage);
        this.welcomeMessageLabel.setFont(new Font("Arial", Font.BOLD, 48));
        this.welcomeMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create new panel to organize main buttons
        JPanel mainButtonsPanel = new JPanel();
        mainButtonsPanel.setLayout(new BoxLayout(mainButtonsPanel, BoxLayout.X_AXIS));

        this.seeInventoryButton = new JButton("Inventory");
        this.seeInventoryButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.seeInventoryButton.setContentAreaFilled(true); 
    	this.seeInventoryButton.setBorderPainted(false); 
    	this.seeInventoryButton.setFocusPainted(false); 
        this.seeInventoryButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.seeInventoryButton.setForeground(Color.WHITE);
        this.seeInventoryButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.notificationsButton = new JButton("Notifications");
        this.notificationsButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.notificationsButton.setContentAreaFilled(true); 
    	this.notificationsButton.setBorderPainted(false); 
    	this.notificationsButton.setFocusPainted(false); 
        this.notificationsButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.notificationsButton.setForeground(Color.WHITE);
        this.notificationsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.ordersButton = new JButton("Orders");
        this.ordersButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.ordersButton.setContentAreaFilled(true); 
    	this.ordersButton.setBorderPainted(false); 
    	this.ordersButton.setFocusPainted(false); 
        this.ordersButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.ordersButton.setForeground(Color.WHITE);
        this.ordersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainButtonsPanel.add(Box.createHorizontalGlue());

        mainButtonsPanel.add(this.seeInventoryButton);
        mainButtonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        mainButtonsPanel.add(this.notificationsButton);
        mainButtonsPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        mainButtonsPanel.add(this.ordersButton);

        mainButtonsPanel.add(Box.createHorizontalGlue());

        this.manageUsersButton = new JButton("Users");
        this.manageUsersButton.setFont(new Font("Arial", Font.PLAIN, 24));
        this.manageUsersButton.setContentAreaFilled(true); 
    	this.manageUsersButton.setBorderPainted(false); 
    	this.manageUsersButton.setFocusPainted(false); 
        this.manageUsersButton.setBackground(new Color(238, 238, 238)); // Set violet color
        this.manageUsersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.manageProductsButton = new JButton("Products");
        this.manageProductsButton.setFont(new Font("Arial", Font.PLAIN, 24));
        this.manageProductsButton.setContentAreaFilled(true); 
    	this.manageProductsButton.setBorderPainted(false); 
    	this.manageProductsButton.setFocusPainted(false); 
        this.manageProductsButton.setBackground(new Color(238, 238, 238)); // Set violet color
        this.manageProductsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.manageSuppliersButton = new JButton("Suppliers");
        this.manageSuppliersButton.setFont(new Font("Arial", Font.PLAIN, 24));
        this.manageSuppliersButton.setContentAreaFilled(true); 
    	this.manageSuppliersButton.setBorderPainted(false); 
    	this.manageSuppliersButton.setFocusPainted(false); 
        this.manageSuppliersButton.setBackground(new Color(238, 238, 238)); // Set violet color
        this.manageSuppliersButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.logOutButton = new JButton("Exit");
        this.logOutButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.logOutButton.setContentAreaFilled(true); 
    	this.logOutButton.setBorderPainted(false); 
    	this.logOutButton.setFocusPainted(false); 
        this.logOutButton.setBackground(new Color(238, 238, 238)); // Set violet color
        this.logOutButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to panel
        homePanel.add(Box.createVerticalGlue());

        homePanel.add(this.welcomeMessageLabel);
        homePanel.add(Box.createRigidArea(new Dimension(0, 100)));
        homePanel.add(mainButtonsPanel);
        homePanel.add(Box.createRigidArea(new Dimension(0, 50)));
        homePanel.add(this.manageUsersButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        homePanel.add(this.manageProductsButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 10)));
        homePanel.add(this.manageSuppliersButton);
        homePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        homePanel.add(this.logOutButton);

        homePanel.add(Box.createVerticalGlue());

        add(homePanel);

        if (userAccesLevel.equals("Assistant")) {
            manageUsersButton.setVisible(false);
        }

        // Button actions
        seeInventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                SeeInventoryView seeInventoryView = new SeeInventoryView(userAccesLevel, welcomeMessage);
                seeInventoryView.showWindow();
            }
        });

        notificationsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                NotificationsView notificationsView = new NotificationsView(userAccesLevel, welcomeMessage);
                notificationsView.showWindow();
            }
        });

        ordersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                OrdersView ordersView = new OrdersView(userAccesLevel, welcomeMessage);
                ordersView.showWindow();
            }
        });

        manageUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManageUsersView manageUsersView = new ManageUsersView(userAccesLevel, welcomeMessage);
                manageUsersView.showWindow();
            }
        });

        manageProductsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManageProductsView manageProductsView = new ManageProductsView(userAccesLevel, welcomeMessage);
                manageProductsView.showWindow();
            }
        });

        manageSuppliersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ManageSuppliersView manageSuppliersView = new ManageSuppliersView(userAccesLevel, welcomeMessage);
                manageSuppliersView.showWindow();
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginView loginView = new LoginView();
                loginView.showWindow();
            }
        });
    }

    public void showWindow() {
        setVisible(true);
    }
}