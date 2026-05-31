package service;

import repository.NotificationRepository;
import model.Notification;

import javax.swing.table.DefaultTableModel;

public class NotificationService {
    private NotificationService() {}
    private static NotificationService instance;

    public static NotificationService getInstance() {
        if (instance == null) {
            instance = new NotificationService();
        }
        return instance;
    }

    private NotificationRepository notificationRepository = NotificationRepository.getInstance();

    public NotificationRepository getNotificationRepository() {
        return this.notificationRepository;
    }

    public void updateTable(DefaultTableModel notificationsTableModel) {
        notificationsTableModel.setRowCount(0);
        
        for (Notification notification : notificationRepository.getNotificationsList()) {
            String[] tableRow = {
                notification.getDate().toString(),
                notification.getDescription()
            };
            notificationsTableModel.addRow(tableRow);
        }

        notificationsTableModel.fireTableDataChanged();
    }
}
