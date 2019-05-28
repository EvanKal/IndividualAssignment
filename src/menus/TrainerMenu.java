/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import dao.IndividualAssignmentDAO;
import dao.StudentInCourseDAO;
import dao.TrainerInCourseDAO;
import java.util.Scanner;
import model.School;
import utils.InputUtils;
import utils.PrintUtils;

/**
 *
 * @author Los_e
 */
public class TrainerMenu {

    public static void trainerMainMenu(Scanner sc, School school) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. View your courses."
                    + "\n2. View all students per course."
                    + "\n3. View all the assignments per students per course."
                    + "\n4. Mark all the assignments per students per course."
                    + "\n5. Log out."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                PrintUtils.printList(TrainerInCourseDAO.getCoursesAppointedToTrainer(school.getLoggedinuser().getUserid()));
            }
            if (choice == 2) {
                PrintUtils.printEntitiesPerEntityStudentInCourse(TrainerInCourseDAO.getStudentsAppointedToTrainer(school.getLoggedinuser().getUserid()));
            }
            if (choice == 3) {
                PrintUtils.printAssignmentsPerStudentPerCourse(IndividualAssignmentDAO.getAllIndividualAssignmentsByTrainer(school.getLoggedinuser().getUserid()));
            }
            if (choice == 4) {
                School.markAssignments(sc, school.getLoggedinuser().getUserid());
            }
            if (choice == 5) {
                school.setLoggedinuser(null);
                check = false;
            }
        }
    }
}
