package model;

import java.time.LocalDate;

public class Notification {
    private LocalDate date;
    private String description;

    public Notification(LocalDate date, String description) {
        this.date = date;
        this.description = description;
    }

    // Getter methods
    public LocalDate getDate() {
        return this.date;
    }

    public String getDescription() {
        return this.description;
    }

    // Setter methods
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}