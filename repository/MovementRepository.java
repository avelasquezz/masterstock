package repository;

import java.util.ArrayList;
import java.util.List;

import model.Movement;

public class MovementRepository {
    private MovementRepository() {}
    private static MovementRepository instance;

    public static MovementRepository getInstance() {
        if (instance == null) {
            instance = new MovementRepository();
        }
        return instance;
    }

    private List<Movement> movementsList = new ArrayList<>();

    public Movement searchMovementById(int id) {
        for (Movement Movement : movementsList) {
            if (Movement.getId() == id) {
                return Movement;
            }
        }
        return null;
    }

    public void addMovement(Movement MovementToAdd) {
        movementsList.add(MovementToAdd);
    }

    public void removeMovement(Movement MovementToRemove) {
        movementsList.remove(MovementToRemove);
    }

    public void updateMovement(Movement modifiedMovement) {
        int modifiedMovementId = modifiedMovement.getId();
        Movement originalMovement = searchMovementById(modifiedMovementId);
        int originalMovementIndex = movementsList.indexOf(originalMovement);

        movementsList.set(originalMovementIndex, modifiedMovement);
    }

    public List<Movement> getMovementsList() {
        return new ArrayList<>(movementsList);
    }
}