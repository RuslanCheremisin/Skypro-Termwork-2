package Planner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;

public class Planner {
    private static Map<Integer, Task> activeTasks = new HashMap<>();

    public static void addTask(String taskName, LocalDateTime taskDateTime, String taskDescription, TaskType taskType, TaskPeriodicity taskPeriodicity) {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);
        if (activeTasks.isEmpty()) {
            try {
                activeTasks.put(1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
                if (activeTasks.get(1).getDateTimeOfExecution().isBefore(LocalDateTime.now())) {
                    activeTasks.get(1).setDeleted(true);
                }
                System.out.println("Задача " + taskName + " на " + taskDateTime.format(dtf) + " добавлена");
            } catch (Exception e) {
                System.out.println("Некоторые поля не заполнены! Повторите создание задачи");
            }

        } else {
            try {
                activeTasks.put(activeTasks.size() + 1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
                if (activeTasks.get(activeTasks.size()).getDateTimeOfExecution().isBefore(LocalDateTime.now())) {
                    activeTasks.get(activeTasks.size()).setDeleted(true);
                }
                System.out.println("Задача " + taskName + " на " + taskDateTime.format(dtf) + " добавлена");
            } catch (IllegalArgumentException e) {
                System.out.println("Некоторые поля не заполнены! Повторите создание задачи");
            }
        }
    }

    public static void removeTaskById(int id) {
        if (activeTasks.containsKey(id)) {
            activeTasks.get(id).setDeleted(true);
            System.out.println("Задача " + activeTasks.get(id).getName() + " переведена в архив!");
        } else {
            System.out.println("Нет такой задачи");
        }
    }

    public static boolean containsTaskById(int id) {
        return activeTasks.containsKey(id);
    }

    public static void editTaskById(int id, String newName, String newDescription) {
        activeTasks.get(id).setName(newName);
        activeTasks.get(id).setDescription(newDescription);
    }


    public static void getTasksByDate(String date) {
        for (Map.Entry<Integer, Task> entry : activeTasks.entrySet()) {
            if (entry.getValue().appearsOn(ConvertUtil.convertStringToDate(date))) {
                System.out.print(entry.getKey() + ": ");
                System.out.println(entry.getValue());
            } else {
                System.out.println("На эту дату задач нет");
            }
        }
    }

    public static void printTaskListsByDates() {
        DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        Map<LocalDate, List<Task>> taskListsByDates = new TreeMap<>();
        for (Task task : activeTasks.values()) {
            taskListsByDates.put(task.getDateTimeOfExecution().toLocalDate(), getTaskListByDate(task.getDateTimeOfExecution().toLocalDate()));
        }
        for (Map.Entry<LocalDate, List<Task>> entry : taskListsByDates.entrySet()) {
            System.out.println(entry.getKey().format(dtf));
            for (Task task : entry.getValue()) {
                if (!task.isDeleted()) {
                    System.out.println(task);
                }
            }
            System.out.println("--------------------------");
        }


    }

    private static List<Task> getTaskListByDate(LocalDate localDate) {
        List<Task> tasksByDate = new ArrayList<>();
        for (Map.Entry<Integer, Task> entry : activeTasks.entrySet()) {
            if (localDate.equals(entry.getValue().getDateTimeOfExecution().toLocalDate())) {
                tasksByDate.add(entry.getValue());
            }
        }
        tasksByDate.sort(Comparator.comparing(task -> task.getDateTimeOfExecution()));
        return tasksByDate;
    }

    public static void printActiveTasks() {

        int activeTasksQty = 0;
        for (Map.Entry<Integer, Task> entry : activeTasks.entrySet()) {
            if (!entry.getValue().isDeleted()) {
                System.out.print(entry.getKey() + ": ");
                System.out.println(entry.getValue());
                activeTasksQty++;
            }
        }
        if (activeTasksQty == 0) {
            System.out.println("В ежедневнике пусто... Добавьте задачу");
        }

    }

    public static void printDeletedTasks() {
        int deletedTasksQty = 0;
        for (Map.Entry<Integer, Task> entry : activeTasks.entrySet()) {
            if (entry.getValue().isDeleted()) {
                System.out.print(entry.getKey() + ": ");
                System.out.println(entry.getValue());
                deletedTasksQty++;
            }
        }
        if (deletedTasksQty == 0) {
            System.out.println("В архиве пусто...");
        }
    }
}
