package Planner;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class Planner {
    private static Map<Integer, Task> activeTasks = new HashMap<>();

    public static void addTask(String taskName, LocalDateTime taskDateTime, String taskDescription, TaskType taskType, TaskPeriodicity taskPeriodicity) {
        if (activeTasks.isEmpty()) {
            try {
                activeTasks.put(1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
            }catch (Exception e){
                System.out.println("Некоторые поля не заполнены! Повторите создание задачи");
            }

        }else{
            try {
                activeTasks.put(activeTasks.size()+1, new Task(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity));
            }catch (Exception e){
                System.out.println("Некоторые поля не заполнены! Повторите создание задачи");
            }
        }
    }

    public static void removeTaskById(int id) {
        if (activeTasks.containsKey(id)) {
            activeTasks.get(id).setDeleted(true);
        }else {
            System.out.println("Нет такой задачи");
        }
    }
    public static boolean containsTaskById(int id){
        if (activeTasks.containsKey(id)) {
            return true;
        }else {
            return false;
        }
    }

    public static void editTaskById(int id, String newName, String newDescription) {
            activeTasks.get(id).setName(newName);
            activeTasks.get(id).setDescription(newDescription);
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
