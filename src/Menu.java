import Planner.ValidateUtil;
import Planner.Planner;
import Planner.TaskType;
import Planner.TaskPeriodicity;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private static String taskName;
    private static String taskDescription;
    private static LocalDateTime taskDateTime;
    private static TaskType taskType;
    private static TaskPeriodicity taskPeriodicity;
    private static Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        int mainMenu;
        do {
            System.out.print("Выберите пункт меню:\n" +
                    "1. Добавить задачу\n" +
                    "2. Изменить задачу по ID\n" +
                    "3. Удалить задачу по ID\n" +
                    "4. Получить задачу на указанную дату\n" +
                    "5. Показать все задачи в ежедневнике\n" +
                    "6. Показать все удалённые задачи в архиве\n" +
                    "0. Выход\n");

            mainMenu = scanner.nextInt();
            switch (mainMenu) {
                case 1:
                    inputTask(scanner);
                    break;
                case 2:
                    editTaskById();
                    break;
                case 3:
                    removeTaskById();
                    break;
                case 4:
                    getTasksByDate();
                    break;
                case 5:
                    Planner.printActiveTasks();
                    break;
                case 6:
                    Planner.printDeletedTasks();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Выберите опцию из предложенных!");
            }
        } while (mainMenu != 0);
    }

    private static void inputTask(Scanner scanner) {
        boolean taskIsDescribed = false;
        String taskDateStr;
        String taskTimeStr;
        do {
            System.out.println("Введите название задачи:");
            taskName = scanner.nextLine();
            taskName = scanner.nextLine();
            System.out.println("Назначьте дату задачи (ДД.ММ.ГГГГ):");
            taskDateStr = ValidateUtil.validateDateDDdotMMdotYYYY(scanner.nextLine());
            System.out.println("Назначьте время задачи (ЧЧ:ММ):");
            taskTimeStr = ValidateUtil.validateTimeHHcolonMM(scanner.nextLine());
            taskDateTime = ValidateUtil.convertStringToDateTime(taskDateStr, taskTimeStr);
            System.out.println("Введите описание задачи:");
            taskDescription = scanner.nextLine();
            System.out.print("Выберите тип задачи:\n" +
                    "1. Личная\n" +
                    "2. Рабочая\n" +
                    "0. Выход\n");
            int taskTypeMenu = scanner.nextInt();
            switch (taskTypeMenu) {
                case 1:
                    taskType = TaskType.PERSONAL;
                    break;
                case 2:
                    taskType = TaskType.WORK;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Выберите опцию из предложенных!\n");
            }
            System.out.print("Выберите периодичность задачи:\n" +
                    "1. Одиночная \n" +
                    "2. Ежедневная \n" +
                    "3. Еженедельная \n" +
                    "4. Ежемесячная \n" +
                    "5. Ежегодная \n" +
                    "0. Выход\n");
            int taskPeriodicityMenu = scanner.nextInt();
            switch (taskPeriodicityMenu) {
                case 1:
                    taskPeriodicity = TaskPeriodicity.SINGLE;
                    break;
                case 2:
                    taskPeriodicity = TaskPeriodicity.DAILY;
                    break;
                case 3:
                    taskPeriodicity = TaskPeriodicity.WEEKLY;
                    break;
                case 4:
                    taskPeriodicity = TaskPeriodicity.MONTHLY;
                    break;
                case 5:
                    taskPeriodicity = TaskPeriodicity.ANNUAL;
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Выберите опцию из предложенных!\n");
            }
            Planner.addTask(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity);
            taskIsDescribed = true;
        } while (taskIsDescribed = false);

    }

    private static void removeTaskById() {
        System.out.println("Введите ID(номер) задачи:");
        int id = scanner.nextInt();
        Planner.removeTaskById(id);

    }

    private static void editTaskById() {
        System.out.println("Введите ID(номер) задачи:");
        int id = scanner.nextInt();
        System.out.println("Введите новое название задачи:");
        String newName = scanner.nextLine();
        newName = scanner.nextLine();
        System.out.println("Введите новое описание задачи:");
        String newDescription = scanner.nextLine();
        Planner.editTaskById(id, newName, newDescription);
        System.out.println("Задача обновлена!");
    }

    private static void getTasksByDate() {
        System.out.print("Введите дату (ДД.ММ.ГГГГ):");
        String date = scanner.next();
        Planner.getTasksByDate(date);
    }

}
