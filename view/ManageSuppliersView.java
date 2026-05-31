package view;

import service.*;
import model.Supplier;
import view.manageSuppliersDialogs.*;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageSuppliersView extends JFrame {
    private UserService userService = UserService.getInstance();
    private ProductService productService = ProductService.getInstance();
    private SupplierService supplierService = SupplierService.getInstance();
    private MovementService movementService = MovementService.getInstance();
    private InventoryService inventoryService = InventoryService.getInstance();
    private NotificationService notificationService = NotificationService.getInstance();
    private OrderService orderService = OrderService.getInstance();
    
    private JLabel suppliersTableTitle;
    private DefaultTableModel suppliersTableModel;
    private JTable suppliersTable;
    private JButton backButton;
    private JButton addSupplierButton;
    private JButton modifySupplierButton;
    private JButton removeSupplierButton;

    public ManageSuppliersView(String userAccesLevel, String welcomeMessage) {
        // Window config
        setTitle("MasterStock | Suppliers");
        setSize(750, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        // UI components
        JPanel tablePanel = new JPanel();
        tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
    
        this.suppliersTableTitle = new JLabel("Suppliers");
        this.suppliersTableTitle.setFont(new Font("Arial", Font.BOLD, 48));
        this.suppliersTableTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] tableColumns = {"Id", "Name", "Address", "Phone number"};
        this.suppliersTableModel = new DefaultTableModel(tableColumns, 0);
        this.suppliersTable = new JTable(suppliersTableModel);

        JTableHeader suppliersTableHeader = this.suppliersTable.getTableHeader();
        suppliersTableHeader.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to panel
        tablePanel.add(Box.createVerticalGlue());
        
        tablePanel.add(this.suppliersTableTitle);
        tablePanel.add(Box.createRigidArea(new Dimension(0, 40)));
        tablePanel.add(new JScrollPane(this.suppliersTable));
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
        
        this.addSupplierButton = new JButton("Add");
        this.addSupplierButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.addSupplierButton.setContentAreaFilled(true); 
    	this.addSupplierButton.setBorderPainted(false); 
    	this.addSupplierButton.setFocusPainted(false); 
        this.addSupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.addSupplierButton.setForeground(Color.WHITE);
        this.addSupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.modifySupplierButton = new JButton("Modify");
        this.modifySupplierButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.modifySupplierButton.setContentAreaFilled(true); 
    	this.modifySupplierButton.setBorderPainted(false); 
    	this.modifySupplierButton.setFocusPainted(false); 
        this.modifySupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.modifySupplierButton.setForeground(Color.WHITE);
        this.modifySupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.removeSupplierButton = new JButton("Remove");
        this.removeSupplierButton.setFont(new Font("Arial", Font.BOLD, 16));
        this.removeSupplierButton.setContentAreaFilled(true); 
    	this.removeSupplierButton.setBorderPainted(false); 
    	this.removeSupplierButton.setFocusPainted(false); 
        this.removeSupplierButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.removeSupplierButton.setForeground(Color.WHITE);
        this.removeSupplierButton.setAlignmentX(Component.CENTER_ALIGNMENT);
                
        // Add components to buttons panel
        buttonsPanel.add(Box.createHorizontalGlue());
        
        buttonsPanel.add(this.backButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(30, 0)));
        buttonsPanel.add(this.addSupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.modifySupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        buttonsPanel.add(this.removeSupplierButton);
        buttonsPanel.add(Box.createRigidArea(new Dimension(10, 0)));
        
        buttonsPanel.add(Box.createHorizontalGlue());
        
        tablePanel.add(buttonsPanel);
        tablePanel.add(Box.createVerticalGlue());

        add(tablePanel);

        if (userAccesLevel.equals("Assistant")) {
            modifySupplierButton.setVisible(false);
            removeSupplierButton.setVisible(false);
        }

        ManageSuppliersView.this.supplierService.updateTable((DefaultTableModel) suppliersTable.getModel());

        // Button actions
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DashboardView dashboardView = new DashboardView(userAccesLevel, welcomeMessage);
                dashboardView.showWindow();
            }
        });

        addSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSupplierDialog addSupplierDialog = new AddSupplierDialog(ManageSuppliersView.this.suppliersTable);
                addSupplierDialog.showDialog();
            }
        });

        modifySupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageSuppliersView.this.suppliersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    ModifySupplierDialog modifySupplierDialog = new ModifySupplierDialog(ManageSuppliersView.this.suppliersTable);
                    modifySupplierDialog.showDialog();
                }
            }
        });

        removeSupplierButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = ManageSuppliersView.this.suppliersTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Supplier produtToRemove = ManageSuppliersView.this.supplierService.getSupplierRepository().searchSupplierById(Integer.parseInt(suppliersTableModel.getValueAt(selectedRow, 0).toString()));
                    ManageSuppliersView.this.supplierService.getSupplierRepository().removeSupplier(produtToRemove);
                    ManageSuppliersView.this.suppliersTableModel.removeRow(selectedRow);
                }
            }
        });
    }
    
    public void showWindow() {
        setVisible(true);
    }
}
