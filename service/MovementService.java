package service;

import repository.MovementRepository;
import model.Movement;

import java.util.Random;
import javax.swing.table.DefaultTableModel;

public class MovementService {
    private MovementService() {}
    private static MovementService instance;

    public static MovementService getInstance() {
        if (instance == null) {
            instance = new MovementService();
        }
        return instance;
    }

    private MovementRepository movementRepository = MovementRepository.getInstance();

    public MovementRepository getMovementRepository() {
        return this.movementRepository;
    }

    public int generateId() {
        Random random = new Random();
        boolean findingNewId = true;
        int newId = 0;

        while(findingNewId) {
            findingNewId = false;
            newId = 1000 + random.nextInt(9000);
            for (Movement movement : this.movementRepository.getMovementsList()) {
                findingNewId = findingNewId || movement.getId() == newId;
            }
        }

        return newId;
    }

    public void updateTable(DefaultTableModel movementsTableModel) {
        movementsTableModel.setRowCount(0);
        
        for (Movement movement : movementRepository.getMovementsList()) {
            String[] tableRow = {
                String.valueOf(movement.getId()), 
                movement.getDate().toString(), 
                movement.getType(),
                String.valueOf(movement.getQuantity()),
                String.valueOf(movement.getUnitPrice()),
                movement.getDescription(), 
                movement.getProduct().getName(), 
            };
            movementsTableModel.addRow(tableRow);
        }

        movementsTableModel.fireTableDataChanged();
    }
}
