/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author Los_e
 */
public class InputUtils {

    public static String inputString(Scanner sc) {
        String input;
        input = sc.nextLine();
//        String regex1 = "^[a-zA-Z]*$";

        while (input.equals("") || input.matches("^\\s*$")) {

//            if (input.equals("") || input.matches("^\\s*$")) {
            System.out.println("No data input. Please type word characters");
            input = sc.nextLine();
//            } else {
//                System.out.println("Invalid data input. Please type word characters");
//                input = sc.nextLine();
//            }

        }

        return input;
    }

    public static String inputStringName(Scanner sc) {
        String input;
        input = sc.nextLine();
        String regex1 = "^[a-zA-Z]*$";

        while (!input.matches(regex1) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type word characters");
                input = sc.nextLine();
            } else {
                System.out.println("Invalid data input. Please type word characters");
                input = sc.nextLine();
            }

        }

        return input;
    }

    public static int inputInt(Scanner sc) {
        String input;
        input = sc.nextLine();
        String regex1 = "^[0-9]*$";

        while (!input.matches(regex1) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type Please type a number.");
                input = sc.nextLine();
            } else {
                System.out.println("Invalid data input. Please type a number.");
                input = sc.nextLine();
            }

        }

        return Integer.parseInt(input);
    }

    public static float inputFloat(Scanner sc) {

        String input;
        input = sc.nextLine();

        String regex1 = "^([+-]?\\d*[.]?\\d*)$";
//    String regex2 = "^\\d*$";

        while (!input.matches(regex1) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type Please type numbers.");
                input = sc.nextLine();
            } else {
                System.out.println("Invalid data input. Please type numbers.");
                input = sc.nextLine();
            }

        }

        return Float.parseFloat(input);

    }

    public static String inputStringType(Scanner sc) {

        String input;
        input = sc.nextLine();
//    String regex1 = "\\d+\\w+";
//    String regex2 = "\\w+\\d+";

        while ((!input.matches("[Ff]ull") && !input.matches("[Pp]art")) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type 'Full' or 'Part'");
                input = sc.nextLine();
            } else {
                System.out.println("Invalid data input. Please type 'Full' or 'Part'");
                input = sc.nextLine();
            }
        }

        if (input.matches("[Ff]ull")) {
            return "Full";
        } else {
            return "Part";
        }
    }

    public static String inputStringStream(Scanner sc) {

        String input;
        input = sc.nextLine();
//    String regex1 = "\\d+\\w+";
//    String regex2 = "\\w+\\d+";

        while ((!input.matches("[Jj]ava") && !input.matches("[Cc]#")) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type 'Java' or 'C#'");
                input = sc.nextLine();
            } else {
                System.out.println("Invalid data input. Please type 'Java' or 'C#'");
                input = sc.nextLine();
            }
        }

        if (input.matches("[Jj]ava")) {
            return "Java";
        } else {
            return "C#";
        }
    }

    public static String inputStringDate(Scanner sc) {

        String input;
        input = sc.nextLine();
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        while (!input.matches(regex) || !Utils.checkValidDate(input) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else if (!input.matches(regex)) {
                System.out.println("Invalid data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else {
                input = sc.nextLine();
            }
        }

        return input;
    }

    public static String inputStringEndDate(Scanner sc, String startdatestr) {

        String input;
        input = sc.nextLine();
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        while (!input.matches(regex) || !Utils.checkValidDate(input) || !Utils.checkDates(startdatestr, input, "End date") || input.equals("") || input.matches("^\\s*$")) {
            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else if (!input.matches(regex)) {
                System.out.println("Invalid data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else {
                input = sc.nextLine();
            }
        }

        return input;

    }
    
    public static String inputStringSubmissionDate(Scanner sc, String startdatestr) {

        String input;
        input = sc.nextLine();
        String regex = "^\\d{4}-\\d{2}-\\d{2}$";

        while (!input.matches(regex) || !Utils.checkValidDate(input) || !Utils.checkDates(startdatestr, input, "Submission date") || input.equals("") || input.matches("^\\s*$")) {
            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else if (!input.matches(regex)) {
                System.out.println("Invalid data input. Please type date in yyyy-mm-dd format.");
                input = sc.nextLine();
            } else {
                input = sc.nextLine();
            }
        }

        return input;

    }

    public static String inputStringTime(Scanner sc) {

        String input;
        input = sc.nextLine();
        String regex = "^\\d{2}:\\d{2}$";

        while (!input.matches(regex) || !Utils.checkValidTime(input) || input.equals("") || input.matches("^\\s*$")) {

            if (input.equals("") || input.matches("^\\s*$")) {
                System.out.println("No data input. Please type date in hh:mm format.");
                input = sc.nextLine();
            } else if (!input.matches(regex)) {
                System.out.println("Invalid data input. Please type date in hh:mm format.");
                input = sc.nextLine();
            } else {
                input = sc.nextLine();
            }
        }

        return input;
    }
}
