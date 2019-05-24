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
