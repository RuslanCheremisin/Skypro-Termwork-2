package Planner;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private TaskType type;
    private TaskPeriodicity periodicity;
    private LocalDateTime dateTimeOfExecution;

    private boolean isDeleted;

    private DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    public Task(String name, LocalDateTime dateTimeOfExecution, String description, TaskType taskType, TaskPeriodicity taskPeriodicity) {
        this.name = ValidateUtil.validateString(name);
        this.description = ValidateUtil.validateString(description);
        this.type = taskType;
        this.periodicity = taskPeriodicity;
        this.dateTimeOfExecution = dateTimeOfExecution;
    }


    boolean appearsOn(LocalDate localDate) {
        if (getDateTimeOfExecution().toLocalDate().isAfter(localDate)) {
            return false;
        }
        return appearsPeriodicallyOn(localDate);
    }

    boolean appearsPeriodicallyOn(LocalDate localDate) {
        LocalDate taskDate = getDateTimeOfExecution().toLocalDate();
        switch (periodicity) {
            case SINGLE:
                return taskDate.equals(localDate);
            case DAILY:
                return taskDate.isBefore(localDate.plusDays(1));
            case WEEKLY:
                return taskDate.isBefore(localDate.plusDays(1)) && taskDate.getDayOfWeek().equals(localDate.getDayOfWeek());
            case MONTHLY:
                return taskDate.isBefore(localDate.plusDays(1)) && taskDate.getDayOfMonth() == localDate.getDayOfMonth();
            case ANNUAL:
                return taskDate.isBefore(localDate.plusDays(1)) && taskDate.getDayOfYear() == localDate.getDayOfYear();
            default:
                return false;
        }

    }

    boolean isDeleted() {
        return isDeleted;
    }

    TaskType getType() {
        return type;
    }

    String getName() {
        return name;
    }

    LocalDateTime getDateTimeOfExecution() {
        return dateTimeOfExecution;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public String toString() {
        String taskTypeStr = "";
        switch (type) {
            case PERSONAL:
                taskTypeStr = "Личная";
                break;
            case WORK:
                taskTypeStr = "Рабочая";
                break;
        }
        String taskPeriodicityStr = "";
        switch (periodicity) {
            case SINGLE:
                taskPeriodicityStr = "Разовая";
                break;
            case DAILY:
                taskPeriodicityStr = "Ежедневная";
                break;
            case WEEKLY:
                taskPeriodicityStr = "Еженедельная";
                break;
            case MONTHLY:
                taskPeriodicityStr = "Ежемесячная";
                break;
            case ANNUAL:
                taskPeriodicityStr = "Ежегодная";
                break;
        }
        return name + "\n" +
                description + "\n" +
                taskTypeStr + "\n" +
                taskPeriodicityStr + "\n" +
                "Первая дата выполнения: " +
                dtf.format(dateTimeOfExecution) +
                "\n--------------------";
    }

    public int hashCode() {
        return Objects.hash(name, description, type, periodicity, dateTimeOfExecution);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(task.name, name) &&
                Objects.equals(task.description, description) &&
                Objects.equals(task.type, type) &&
                Objects.equals(task.periodicity, periodicity) &&
                Objects.equals(task.dateTimeOfExecution, dateTimeOfExecution);
    }
}
