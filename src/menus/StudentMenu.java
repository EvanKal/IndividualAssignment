/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import dao.IndividualAssignmentDAO;
import dao.StudentInCourseDAO;
import java.util.Scanner;
import model.School;
import utils.InputUtils;
import utils.PrintUtils;

/**
 *
 * @author Los_e
 */
public class StudentMenu {
    
    public static void studentMainMenu(Scanner sc, School school) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Enroll to course."
                    + "\n2. View daily schedule per course."
                    + "\n3. See the dates of submission of the Assignments per Course."
                    + "\n4. Submit an assignment."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                School.enrollStudentToCourses(sc, school.getLoggedinuser().getUserid());
            }
            if (choice == 2) {
                PrintUtils.printListCoursesDailyScheduleForStudent(StudentInCourseDAO.getAllCoursesPerStudent(school.getLoggedinuser().getUserid()));
            }
            if (choice == 3) {
                PrintUtils.printListAssignmentsForStudent(IndividualAssignmentDAO.getAllIndividualAssignmentsByStudentId(school.getLoggedinuser().getUserid()));
            }
            if (choice == 4) {
                School.submitAssignment(sc, school.getLoggedinuser().getUserid());
            }
//            if(choice == 5) {this.printAccessEntity("student", "students", this.studentList);}
//            if(choice == 6) {this.printAccessEntity("course", "courses", this.courseList);}
//            if(choice == 7) {this.printAccessEntity("trainer", "trainers", this.trainerList);}
//            if(choice == 8) {this.printAccessEntity("assignment", "assignments", this.assignmentList);}
            if (choice == 5) {
                check = false;
            }
//            if(choice == 10) {System.out.println("Printing student's enrolled to more than one course: "); this.printStudentsToMoreThanOneCourse(this.studentList);}
//            if(choice == 11) {this.createSyntheticData();}
        }
    }
}
