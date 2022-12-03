import Planner.ValidateUtil;
import Planner.ConvertUtil;
import Planner.Planner;
import Planner.TaskType;
import Planner.TaskPeriodicity;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Menu {
    private static TaskType taskType;
    private static TaskPeriodicity taskPeriodicity;
    private static final Scanner scanner = new Scanner(System.in);

    public static void mainMenu() {
        String mainMenu;
        System.out.println("Добро пожаловать в консольную модель ежедневника.\n" +
                "При добавлении задач постарайтесь заполнять все поля корректно, по инструкции, если такая прилагается.\n" +
                "Незаполненные поля приведут к предотвращению создания задачи.\n" +
                "NB. Если вы присвоите задаче дату и время раньше текущего, то она будет автоматически переведена в архив.\n" +
                "Приятного теста!\n");
        do {
            System.out.print(
                    "Выберите пункт меню:\n" +
                            "1. Добавить задачу\n" +
                            "2. Изменить задачу по ID\n" +
                            "3. Удалить задачу по ID\n" +
                            "4. Получить задачу на указанную дату\n" +
                            "5. Показать задачи, сгруппированные по датам первого исполнения\n" +
                            "6. Показать все задачи в ежедневнике\n" +
                            "7. Показать все удалённые задачи в архиве\n" +
                            "0. Выход\n");

            mainMenu = scanner.nextLine();
            switch (mainMenu) {
                case "1":
                    inputTask(scanner);
                    break;
                case "2":
                    editTaskById();
                    break;
                case "3":
                    removeTaskById();
                    break;
                case "4":
                    getTasksByDate();
                    scanner.nextLine();
                    break;
                case "5":
                    Planner.printTaskListsByDates();
                    break;
                case "6":
                    Planner.printActiveTasks();
                    break;
                case "7":
                    Planner.printDeletedTasks();
                    break;
                case "0":
                    break;
                default:
                    System.out.println("Выберите опцию из предложенных!");
            }
        } while (!mainMenu.equals("0"));
    }

    private static void inputTask(Scanner scanner) {
        String taskDateStr = "";
        String taskTimeStr = "";
        while (true) {
            System.out.println("Введите название задачи:");
            String taskName = scanner.nextLine();
            while (true) {
                System.out.println("Назначьте дату задачи (ДД.ММ.ГГГГ):");
                try {
                    taskDateStr = ValidateUtil.validateDateDDdotMMdotYYYY(scanner.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Неправильный формат даты! Пожалуйста, используйте формат ДД.ММ.ГГГГ(точки)");
                }
            }
            while (true) {
                System.out.println("Назначьте время задачи (ЧЧ:ММ):");
                try {
                    taskTimeStr = ValidateUtil.validateTimeHHcolonmm(scanner.nextLine());
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Неправильный формат времени! Пожалуйста, используйте формат ЧЧ:ММ(двоеточие)");
                }
            }

            LocalDateTime taskDateTime = ConvertUtil.convertStringToDateTime(taskDateStr, taskTimeStr);
            System.out.println("Введите описание задачи:");
            String taskDescription = scanner.nextLine();
            String taskTypeMenu = "";
            label:
            while (true) {
                System.out.print("Выберите тип задачи:\n" +
                        "1. Личная\n" +
                        "2. Рабочая\n" +
                        "0. Выход\n");
                taskTypeMenu = scanner.nextLine();
                switch (taskTypeMenu) {
                    case "1":
                        taskType = TaskType.PERSONAL;
                        break label;
                    case "2":
                        taskType = TaskType.WORK;
                        break label;
                    case "0":
                        break label;
                    default:
                        System.out.println("Выберите опцию из предложенных!");
                }
            }
            String taskPeriodicityMenu = "";
            label1:
            while (true) {
                System.out.print("Выберите периодичность задачи:\n" +
                        "1. Одиночная \n" +
                        "2. Ежедневная \n" +
                        "3. Еженедельная \n" +
                        "4. Ежемесячная \n" +
                        "5. Ежегодная \n" +
                        "0. Выход\n");
                taskPeriodicityMenu = scanner.nextLine();
                switch (taskPeriodicityMenu) {
                    case "1":
                        taskPeriodicity = TaskPeriodicity.SINGLE;
                        break label1;
                    case "2":
                        taskPeriodicity = TaskPeriodicity.DAILY;
                        break label1;
                    case "3":
                        taskPeriodicity = TaskPeriodicity.WEEKLY;
                        break label1;
                    case "4":
                        taskPeriodicity = TaskPeriodicity.MONTHLY;
                        break label1;
                    case "5":
                        taskPeriodicity = TaskPeriodicity.ANNUAL;
                        break label1;
                    case "0":
                        break label1;
                    default:
                        System.out.println("Выберите опцию из предложенных!");
                }
            }
            Planner.addTask(taskName, taskDateTime, taskDescription, taskType, taskPeriodicity);
            return;
        }

    }

    private static void removeTaskById() {
        System.out.println("Введите ID(номер) задачи:");
        String idStr = scanner.nextLine();
        try {
            if (Planner.containsTaskById(Integer.parseInt(idStr))) {
                Planner.removeTaskById(Integer.parseInt(idStr));
            } else {
                System.out.println("Нет задачи с таким ID.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Введите корректный ID");
        }

    }

    private static void editTaskById() {
        System.out.println("Введите ID(номер) задачи:");
        String idStr = scanner.nextLine();
        try {
            if (Planner.containsTaskById(Integer.parseInt(idStr))) {
                System.out.println("Введите новое название задачи:");
                String newName = scanner.nextLine();
                System.out.println("Введите новое описание задачи:");
                String newDescription = scanner.nextLine();
                Planner.editTaskById(Integer.parseInt(idStr), newName, newDescription);
                System.out.println("Задача обновлена!");

            } else {
                System.out.println("Нет задачи с таким ID");
            }

        } catch (NumberFormatException e) {
            System.out.println("Введите корректный ID");
        }
    }

    private static void getTasksByDate() {
        System.out.print("Введите дату (ДД.ММ.ГГГГ):");
        try {
            String date = ValidateUtil.validateDateDDdotMMdotYYYY(scanner.next());
            Planner.getTasksByDate(date);
        } catch (IllegalArgumentException e) {
            System.out.println("Неправильный формат даты! Пожалуйста, используйте формат ДД.ММ.ГГГГ(точки)");
        }


    }

}
