package Planner;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateUtil {
    public static String validateString(String value) {
        if (value != null && !value.isEmpty() && !value.isBlank()) {
            return value;
        } else {
//            throw new NoDriverLicenseException("Incorrect entry");
//            return "No data";
            throw new IllegalArgumentException("Проверьте незаполненные поля!");


        }
    }

    public static int validateInt(int value) {
        if (value > 0) {
            return value;
        } else {
            return 0;
        }
    }

    public static double validateDouble(double value) {
        if (value > 0) {
            return value;
        } else {
            return 0;
        }
    }

    public static char validateChar(char value) {
        if (value == 'A' || value == 'B' || value == 'C' || value == 'D') {
            return value;
        } else {
            return 'X';
        }
    }

    public static String validateDateDDdotMMdotYYYY(String date) {
        Pattern p = Pattern.compile("\\d{2}+\\.{1}+\\d{2}+\\.{1}+\\d{4}");
        Matcher m = p.matcher(date);
        if (m.matches() &&
                Integer.parseInt(date.substring(0, 2)) < 32 &&
                Integer.parseInt(date.substring(0, 2)) > 0 &&
                Integer.parseInt(date.substring(3, 5)) < 13 &&
                Integer.parseInt(date.substring(3, 5)) > 0 &&
//                Integer.parseInt(date.substring(6))<= LocalDate.now().getYear() &&
                Integer.parseInt(date.substring(6)) >= LocalDate.now().getYear() - 120) {
            return date;

        } else {
            throw new IllegalArgumentException("Wrong date format! Please use DD.MM.YYYY format(dots, not commas)");
//            return "Wrong date format! Please use DD.MM.YYYY format(dots, not commas)";
        }
    }
    public static String validateTimeHHcolonmm(String time){
        Pattern p1 = Pattern.compile("\\d{2}+\\:{1}+\\d{2}");
        Pattern p2 = Pattern.compile("\\d{1}+\\:{1}+\\d{2}");
        Matcher m1 = p1.matcher(time);
        Matcher m2 = p2.matcher(time);
        if (m1.matches() &&
                Integer.parseInt(time.substring(0, 2)) < 24 &&
                Integer.parseInt(time.substring(0, 2)) >= 0 &&
                Integer.parseInt(time.substring(3)) < 60 &&
                Integer.parseInt(time.substring(3)) >= 0){
            return time;
        }else if(m2.matches() &&
                Integer.parseInt(time.substring(0, 1)) < 10 &&
                Integer.parseInt(time.substring(0, 1)) >= 0 &&
                Integer.parseInt(time.substring(2)) < 60 &&
                Integer.parseInt(time.substring(2)) >= 0){
            return time;
        }
        else {
            throw new IllegalArgumentException("Wrong time format! Please use HH:MM(H:MM) format(colon in between)");
//            return "Wrong date format! Please use DD.MM.YYYY format(dots, not commas)";
        }
    }


}