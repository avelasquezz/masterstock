package view.manageUsersDialogs;

import service.UserService;
import model.User;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ModifyUserDialog extends JDialog {
    private UserService userService = UserService.getInstance();
    
    private JLabel idTypeTextFieldLabel;
    private JTextField idTypeTextField;
    private JLabel idNumberTextFieldLabel;
    private JTextField idNumberTextField;
    private JLabel nameTextFieldLabel;
    private JTextField nameTextField;
    private JLabel lastNameTextFieldLabel;
    private JTextField lastNameTextField;
    private JLabel phoneNumberTextFieldLabel;
    private JTextField phoneNumberTextField;
    private JLabel emailAddressTextFieldLabel;
    private JTextField emailAddressTextField;
    private JLabel passwordTextFieldLabel;
    private JTextField passwordTextField;
    private JLabel stateComboBoxLabel;
    private JComboBox<String> stateComboBox;
    private JLabel accesLevelComboBoxLabel;
    private JComboBox<String> accesLevelComboBox;
    private JButton acceptButton;
    private JLabel errorMessageLabel;

    public ModifyUserDialog(JTable usersTable) {
        // Dialog config
        setTitle("Modify user");
        setSize(500, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // UI components
        JPanel dialogPanel = new JPanel();
        dialogPanel.setLayout(new BoxLayout(dialogPanel, BoxLayout.Y_AXIS));
        dialogPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        dialogPanel.setBorder(new EmptyBorder(20, 10, 20, 10));
        
        this.idTypeTextFieldLabel = new JLabel("ID Type");
        this.idTypeTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idTypeTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idTypeTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 0));
        this.idTypeTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.idTypeTextField.setMaximumSize(new Dimension(200, 40));
        this.idTypeTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.idNumberTextFieldLabel = new JLabel("ID Number");
        this.idNumberTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.idNumberTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.idNumberTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 1));
        this.idNumberTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.idNumberTextField.setMaximumSize(new Dimension(200, 40));
        this.idNumberTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.nameTextFieldLabel = new JLabel("Name");
        this.nameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.nameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.nameTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 2));
        this.nameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.nameTextField.setMaximumSize(new Dimension(200, 40));
        this.nameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        this.lastNameTextFieldLabel = new JLabel("Last name");
        this.lastNameTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.lastNameTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.lastNameTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 3));
        this.lastNameTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.lastNameTextField.setMaximumSize(new Dimension(200, 40));
        this.lastNameTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.phoneNumberTextFieldLabel = new JLabel("Phone number");
        this.phoneNumberTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.phoneNumberTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.phoneNumberTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 4));
        this.phoneNumberTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.phoneNumberTextField.setMaximumSize(new Dimension(200, 40));
        this.phoneNumberTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.emailAddressTextFieldLabel = new JLabel("Email address");
        this.emailAddressTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.emailAddressTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.emailAddressTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 5));
        this.emailAddressTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.emailAddressTextField.setMaximumSize(new Dimension(200, 40));
        this.emailAddressTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.passwordTextFieldLabel = new JLabel("Password");
        this.passwordTextFieldLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.passwordTextFieldLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        this.passwordTextField = new JTextField((String) usersTable.getValueAt(usersTable.getSelectedRow(), 6));
        this.passwordTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        this.passwordTextField.setMaximumSize(new Dimension(200, 40));
        this.passwordTextField.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.stateComboBoxLabel = new JLabel("State");
        this.stateComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.stateComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] stateOptions = {"Active", "Inactive"};
        this.stateComboBox = new JComboBox<>(stateOptions);
        this.stateComboBox.setSelectedItem((String) usersTable.getValueAt(usersTable.getSelectedRow(), 7));
        this.stateComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.stateComboBox.setMaximumSize(new Dimension(200, 40));

        this.accesLevelComboBoxLabel = new JLabel("Access level");
        this.accesLevelComboBoxLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        this.accesLevelComboBoxLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        String[] accesLevelOptions = {"Administrator", "Assistant"};
        this.accesLevelComboBox = new JComboBox<>(accesLevelOptions);
        this.accesLevelComboBox.setSelectedItem((String) usersTable.getValueAt(usersTable.getSelectedRow(), 8));
        this.accesLevelComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        this.accesLevelComboBox.setMaximumSize(new Dimension(200, 40));

        this.acceptButton = new JButton("Accept");
        this.acceptButton.setFont(new Font("Arial", Font.BOLD, 24));
        this.acceptButton.setContentAreaFilled(true); 
    	this.acceptButton.setBorderPainted(false); 
    	this.acceptButton.setFocusPainted(false); 
        this.acceptButton.setBackground(new Color(175, 128, 232)); // Set violet color
        this.acceptButton.setForeground(Color.WHITE);
        this.acceptButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.errorMessageLabel = new JLabel("");
        this.errorMessageLabel.setFont(new Font("Arial", Font.BOLD, 14));
        this.errorMessageLabel.setForeground(new Color(235, 87, 87)); // Set red color
        this.errorMessageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Create sub-panel to organize elements
        JPanel mainPanel = new JPanel(new GridLayout(1, 2, 20, 0)); 

        // Left column panel
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS)); 
        leftPanel.setBorder(new EmptyBorder(20, 10, 0, 10)); 

        // Right column panel
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS)); 
        rightPanel.setBorder(new EmptyBorder(20, 10, 0, 10));
    
        // Add components to left panel
        leftPanel.add(this.idTypeTextFieldLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(this.idTypeTextField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(this.idNumberTextFieldLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(this.idNumberTextField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(this.nameTextFieldLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(this.nameTextField);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        leftPanel.add(this.lastNameTextFieldLabel);
        leftPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        leftPanel.add(this.lastNameTextField);

        // Add components to right panel
        rightPanel.add(this.phoneNumberTextFieldLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(this.phoneNumberTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(this.emailAddressTextFieldLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(this.emailAddressTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(this.passwordTextFieldLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(this.passwordTextField);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        rightPanel.add(this.accesLevelComboBoxLabel);
        rightPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        rightPanel.add(this.accesLevelComboBox);

        // Add components to main panel
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        // Create panel to organize buttons
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));

        bottomPanel.add(this.stateComboBoxLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomPanel.add(this.stateComboBox);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 40)));
        bottomPanel.add(this.acceptButton);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomPanel.add(this.errorMessageLabel);

        // Add everythin to dialog
        dialogPanel.add(Box.createVerticalGlue());

        dialogPanel.add(mainPanel);
        dialogPanel.add(bottomPanel);

        dialogPanel.add(Box.createVerticalGlue());

        add(dialogPanel);

        // Button actions
        acceptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String newUserIdType = ModifyUserDialog.this.idTypeTextField.getText();
                    int newUserIdNumber = Integer.parseInt(ModifyUserDialog.this.idNumberTextField.getText());
                    String newUserName = ModifyUserDialog.this.nameTextField.getText();
                    String newUserLastName = ModifyUserDialog.this.lastNameTextField.getText();
                    String newUserPhoneNumber = ModifyUserDialog.this.phoneNumberTextField.getText();
                    String newUserEmailAddress = ModifyUserDialog.this.emailAddressTextField.getText();
                    String newUserPassword = ModifyUserDialog.this.passwordTextField.getText();
                    boolean newUserState = ModifyUserDialog.this.stateComboBox.getSelectedItem().toString() == "Active" ? true : false;
                    String newUserAccesLevel = ModifyUserDialog.this.accesLevelComboBox.getSelectedItem().toString();
            
                    User modifiedUser = new User(newUserIdType, newUserIdNumber, newUserName, newUserLastName, newUserPhoneNumber, newUserEmailAddress, newUserPassword, newUserState, newUserAccesLevel);
            
                    ModifyUserDialog.this.userService.getUserRepository().updateUser(modifiedUser);
                    ModifyUserDialog.this.userService.updateTable((DefaultTableModel) usersTable.getModel());

                    dispose();
                } catch (NumberFormatException ex) {
					ModifyUserDialog.this.errorMessageLabel.setText("Invalid data was detected.");
				}
            }
        });
    }

    public void showDialog() {
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
