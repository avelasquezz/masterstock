package service;

import repository.UserRepository;

import javax.swing.table.DefaultTableModel;

import model.User;

public class UserService {
    private UserService() {}
    private static UserService instance;

    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    private UserRepository userRepository = UserRepository.getInstance();

    public UserRepository getUserRepository() {
        return this.userRepository;
    }

    public boolean validateUser(String emailAddress, String password) {
        User userToValidate = this.userRepository.searchUserByEmailAddress(emailAddress);
        if (userToValidate == null) {
            return false;
        }
        return userToValidate.getPassword().equals(password);
    }

    public void updateTable(DefaultTableModel productsTableModel) {
        productsTableModel.setRowCount(0);

        for (User user : userRepository.getUsersList()) {
            String[] tableRow = {
                String.valueOf(user.getIdNumber()),
                user.getName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getEmailAddress(),
                user.getPassword(),
                (user.getState() == true) ? "Activo" : "Inactivo",
                user.getAccesLevel()
            };
            productsTableModel.addRow(tableRow);
        }

        productsTableModel.fireTableDataChanged();
    }
}