package Planner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Planner {
    private static Map<Integer, Task> activeTasks = new HashMap<>();

    public static void addTask(String taskName, LocalDateTime taskDateTime, String taskDescription, TaskType taskType, TaskPeriodicity taskPeriodicity) {
        if (activeTasks.isEmpty()) {
            activeTasks.put(1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
        }else{
            activeTasks.put(activeTasks.size()+1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
        }
    }

    public static void removeTaskById(int id) {
        if (activeTasks.containsKey(id)) {
            activeTasks.get(id).setDeleted(true);
        }else {
            System.out.println("Нет такой задачи");
        }
    }

    public static void editTaskById(int id, String newName, String newDescription) {
        if (activeTasks.containsKey(id)) {
            activeTasks.get(id).setName(newName);
            activeTasks.get(id).setDescription(newDescription);
        }
    }



    public static void getTasksByDate(String date) {
        for (Map.Entry<Integer, Task> entry: activeTasks.entrySet()){
            if (entry.getValue().appearsOn(ValidateUtil.convertStringToDate(date))) {
                System.out.print(entry.getKey()+": ");
                System.out.println(entry.getValue());
            }
        }
    }

    public static void printActiveTasks() {
        for (Map.Entry<Integer, Task> entry: activeTasks.entrySet()) {
            if (!entry.getValue().isDeleted()){
                System.out.print(entry.getKey()+": ");
                System.out.println(entry.getValue());
            }
        }
    }

    public static void printDeletedTasks() {
        for (Map.Entry<Integer, Task> entry: activeTasks.entrySet()) {
            if (entry.getValue().isDeleted()){
                System.out.print(entry.getKey()+": ");
                System.out.println(entry.getValue());
            }
        }
    }
}
