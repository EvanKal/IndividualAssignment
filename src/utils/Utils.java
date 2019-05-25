/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import dao.UserDAO;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import model.Assignment;
import model.Course;
import model.Student;
import model.Trainer;
import model.User;

/**
 *
 * @author Los_e
 */
public class Utils {

    public static boolean checkValidDate(String date) {

        try {
            LocalDate datetotest = LocalDate.parse(date);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date!");
            return false;
        }
    }

    public static boolean checkDates(String startdatestr, String enddatestr) {
        LocalDate startdate = LocalDate.parse(startdatestr);
        LocalDate enddate = LocalDate.parse(enddatestr);

        if (startdate.isAfter(enddate)) {
            System.out.println("End date " + enddate + " can't be before start date " + startdate);
            return false;
        } else {
            return true;
        }
    }

    public static boolean checkValidTime(String time) {

        try {
            LocalTime timetotest = LocalTime.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time!");
            return false;
        }
    }

    public static ArrayList<Integer> printListElementsAndCollectChoices(Scanner sc, ArrayList arraylist) {

        //Returns an arrayList of integers corresponding to the indexes of the user's choices
        String input;
        boolean check = true;
        ArrayList<Integer> choices = new ArrayList<>();

        PrintUtils.printList(arraylist);
        System.out.println("Choose one " + arraylist.get(0).getClass().getSimpleName() + " or more by typing their item number. When done type 'End'. If you want to add all of the students, type 'All'.");

        while (check) {
            input = sc.nextLine();
            String regex1 = "^[0-9]*$";

            if (!input.matches(regex1) || input.equals("")) {
                if (input.matches("[Ee]nd")) {
                    check = false;
                } else if (input.matches("[Aa]ll")) {
                    choices.clear();
                    for (int i = 0; i < arraylist.size(); i++) {
                        choices.add(i + 1);
                    }
                    System.out.println("Added all elements in list.");
                    check = false;
                } else {
                    System.out.println("Invalid data input. Please type an integer");
                }
            } else if (!(Integer.parseInt(input) <= arraylist.size()) || Integer.parseInt(input) == 0) {
                System.out.println("Invalid data input. Invalid integer (integer cannot be greater than the size of the list, or zero)");
            } else {
                choices.add(Integer.parseInt(input));
            }
        }

        return choices;
    }

    public static int printListElementsAndPickOne(Scanner sc, ArrayList arraylist) {

        //Returns an integer corresponding to the index of the user's choice
        String input;
        boolean check = true;
        int choice = 0;

        PrintUtils.printList(arraylist);
        System.out.println("Choose one " + arraylist.get(0).getClass().getSimpleName() + " by typing their item number or type 'End' to exit.");

        while (check) {
            input = sc.nextLine();
            String regex1 = "^[0-9]*$";

            if (!input.matches(regex1) || input.equals("")) {
                if (input.matches("[Ee]nd")) {
                    check = false;
                } else {
                    System.out.println("Invalid data input. Please type an integer");
                }
            } else if (!(Integer.parseInt(input) <= arraylist.size()) || Integer.parseInt(input) == 0) {
                System.out.println("Invalid data input. Invalid integer (integer cannot be greater than the size of the list, or zero)");
            } else {
                choice = Integer.parseInt(input);
                check = false;

            }
        }

        return choice;
    }

    public static boolean checkDeadline(LocalDateTime localdatetime, String requesteddatestr) {

        LocalDate requesteddate = LocalDate.parse(requesteddatestr);
        LocalTime requestedtime = LocalTime.parse("23:59");
        LocalDateTime requestedlocaldate = LocalDateTime.of(requesteddate, requestedtime);

        int dayenum = DayOfWeek.from(localdatetime).getValue();
        LocalDateTime dateofthefirstdayoftheweek = localdatetime.minusDays(dayenum - 1);
        LocalDateTime dateofthelastdayoftheweek = localdatetime.plusDays(7 - dayenum);

        if (!requestedlocaldate.isBefore(dateofthefirstdayoftheweek) && !requestedlocaldate.isAfter(dateofthelastdayoftheweek)) {
            return true;
        } else {
            return false;
        }

    }

    public static void hashPasswordsInDatabase(ArrayList<User> arraylist) {

        for (User user : arraylist) {

            String bcryptHashString = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());
            user.setPassword(bcryptHashString);

            UserDAO.updateUser(user);

        }

    }

    public static User logIn(Scanner sc) {

        System.out.println("Logging in...");
        System.out.println("Username: ");
        String username = InputUtils.inputString(sc);

        while (UserDAO.checkIfUsernameExists(username) == 0) {
            System.out.println("No user found with username " + username + ". Try a different one: ");
            username = InputUtils.inputString(sc);
        }
        User user = UserDAO.getUserTryingToLogIn(username);

        System.out.println("Password: ");
        String password = InputUtils.inputString(sc);
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

        while (!result.verified) {
            System.out.println("Wrong password. Try again: ");
            password = InputUtils.inputString(sc);
            result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        }

        System.out.println("Successfully logged in as: " + user.toString());

        return user;
    }

    public static User DefaultLogInAsHeadmaster() {

        System.out.println("Logging in as headmaster...");
        String username = "headmaster1";

        User user = UserDAO.getUserTryingToLogIn(username);

        if (user.getUsername() != null) {
            System.out.println("Successfully logged in as: " + user.toString());
        } else {
            System.out.println("Failed to log in as headmaster. Something went wrong.");
        }
        return user;
    }

    public static void editEntity(Scanner sc, Student student) {

        //Overloaded method
        boolean check = true;
        boolean domorestuff = false;

        while (check) {

            if (!domorestuff) {
                System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                        + "\n1. Set first name"
                        + "\n2. Set last name."
                        + "\n3. Set birth date."
                        + "\n4. Set tuition fees."
                        + "\n5. Set stream."
                        + "\n6. Set type."
                        + "\n7. Done editing.");
            } else {
                System.out.println("\nWant to edit more?\n"
                        + "\n1. Set first name"
                        + "\n2. Set last name."
                        + "\n3. Set birth date."
                        + "\n4. Set tuition fees."
                        + "\n5. Set stream."
                        + "\n6. Set type."
                        + "\n7. Done editing.");
            }

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 7) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                System.out.println("First name:");
                student.setFirstname(InputUtils.inputStringName(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 2) {
                System.out.println("Last name:");
                student.setLastname(InputUtils.inputStringName(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 3) {
                System.out.println("Date of birth:");
                student.setDateofbirth(InputUtils.inputStringDate(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 4) {
                System.out.println("Tuition fees:");
                student.setTuitionfees(InputUtils.inputFloat(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 5) {
                System.out.println("Stream:");
                student.setStream(InputUtils.inputStringStream(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 6) {
                System.out.println("Type:");
                student.setStream(InputUtils.inputStringType(sc));
                domorestuff = true;
                System.out.println(student.toString());
            }
            if (choice == 7) {
                System.out.println("Exiting...");
                check = false;
                domorestuff = false;
            }

        }
    }
    
    public static void editEntity(Scanner sc, Course course) {

        //Overloaded method
        boolean check = true;
        boolean domorestuff = false;

        while (check) {

            if (!domorestuff) {
                System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                        + "\n1. Set Title"
                        + "\n2. Set stream."
                        + "\n3. Set type."
                        + "\n4. Set start date."
                        + "\n5. Set end date."
                        + "\n6. Done editing.");
            } else {
                System.out.println("\nWant to edit more?\n"
                        + "\n1. Set Title"
                        + "\n2. Set stream."
                        + "\n3. Set type."
                        + "\n4. Set start date."
                        + "\n5. Set end date."
                        + "\n6. Done editing.");
            }

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 6) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                System.out.println("Title:");
                course.setTitle(InputUtils.inputString(sc));
                domorestuff = true;
                System.out.println(course.toString());
            }
            if (choice == 2) {
                System.out.println("Stream:");
                course.setStream(InputUtils.inputStringStream(sc));
                domorestuff = true;
                System.out.println(course.toString());
            }
            if (choice == 3) {
                System.out.println("Type:");
                course.setStream(InputUtils.inputStringType(sc));
                domorestuff = true;
                System.out.println(course.toString());
            }
            if (choice == 4) {
                System.out.println("Start date:");
                course.setStartdate(InputUtils.inputStringDate(sc));
                domorestuff = true;
                System.out.println(course.toString());
            }
            if (choice == 5) {
                System.out.println("End date:");
                course.setEnddate(InputUtils.inputStringEndDate(sc, course.getStartdate().toString()));
                domorestuff = true;
                System.out.println(course.toString());
            }
            if (choice == 6) {
                System.out.println("Exiting...");
                check = false;
                domorestuff = false;
            }

        }
    }
    
    public static void editEntity(Scanner sc, Assignment assignment) {

        //Overloaded method
        boolean check = true;
        boolean domorestuff = false;

        while (check) {

            if (!domorestuff) {
                System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                        + "\n1. Set Title"
                        + "\n2. Set description."
                        + "\n3. Done editing.");
            } else {
                System.out.println("\nWant to edit more?\n"
                        + "\n1. Set Title"
                        + "\n2. Set description."
                        + "\n3. Done editing.");
            }

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 3) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                System.out.println("Title:");
                assignment.setTitle(InputUtils.inputString(sc));
                domorestuff = true;
                System.out.println(assignment.toString());
            }
            if (choice == 2) {
                System.out.println("Description:");
                assignment.setDescription(InputUtils.inputString(sc));
                domorestuff = true;
                System.out.println(assignment.toString());
            }
            if (choice == 3) {
                System.out.println("Exiting...");
                check = false;
                domorestuff = false;
            }

        }
    }

    public static void editEntity(Scanner sc, Trainer trainer) {

        //Overloaded method
        boolean check = true;
        boolean domorestuff = false;

        while (check) {

            if (!domorestuff) {
                System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                        + "\n1. Set first name"
                        + "\n2. Set last name."
                        + "\n3. Set subject."
                        + "\n4. Done editing.");
            } else {
                System.out.println("\nWant to edit more?\n"
                        + "\n1. Set first name"
                        + "\n2. Set last name."
                        + "\n3. Set subject."
                        + "\n4. Done editing.");
            }

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                System.out.println("First name:");
                trainer.setFirstname(InputUtils.inputStringName(sc));
                domorestuff = true;
                System.out.println(trainer.toString());
            }
            if (choice == 2) {
                System.out.println("Last name:");
                trainer.setLastname(InputUtils.inputStringName(sc));
                domorestuff = true;
                System.out.println(trainer.toString());
            }
            if (choice == 3) {
                System.out.println("Subject:");
                trainer.setSubject(InputUtils.inputStringDate(sc));
                domorestuff = true;
                System.out.println(trainer.toString());
            }
            if (choice == 4) {
                System.out.println("Exiting...");
                check = false;
                domorestuff = false;
            }

        }
    }

//    public static User logIn(Scanner sc) {
//
//        System.out.println("Logging in...");
//        System.out.println("Username: ");
//        String username = InputUtils.inputString(sc);
//        User user = UserDAO.getUserTryingToLogIn(username);
//
//        while (user.getUsername() == null) {
//            System.out.println("No user found with username " + username + ". Try a different one: ");
//            username = InputUtils.inputString(sc);
//            user = UserDAO.getUserTryingToLogIn(username);
//        }
//
//        System.out.println("Password: ");
//        String password = InputUtils.inputString(sc);
//        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
//
//        while (!result.verified) {
//            System.out.println("Wrong password. Try again: ");
//            password = InputUtils.inputString(sc);
//            result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
//        }
//
//        System.out.println("Successfully logged in as: " + user.toString());
//
//        return user;
//    }
}
