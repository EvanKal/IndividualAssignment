/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import dao.AssignmentDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import dao.TrainerDAO;
import java.util.Scanner;
import model.School;
import utils.InputUtils;
import utils.PrintUtils;

/**
 *
 * @author Los_e
 */
public class HeadmasterMenu {

    public static void headmasterMainMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. CRUD on students."
                    + "\n2. CRUD on courses."
                    + "\n3. CRUD on trainers."
                    + "\n4. CRUD on assignments."
                    + "\n5. CRUD on students per courses."
                    + "\n6. CRUD on trainers per courses."
                    + "\n7. CRUD on assignments per courses."
                    + "\n8. CRUD on schedule per courses."
                    + "\n9. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 9) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {
                HeadmasterMenu.studentsCRUDMenu(sc);
            }
            if (choice == 2) {
                HeadmasterMenu.coursesCRUDMenu(sc);
            }
            if (choice == 3) {
                HeadmasterMenu.trainersCRUDMenu(sc);
            }
            if (choice == 4) {
                HeadmasterMenu.assignmentsCRUDMenu(sc);
            }
//            if(choice == 5) {this.printAccessEntity("student", "students", this.studentList);}
//            if(choice == 6) {this.printAccessEntity("course", "courses", this.courseList);}
//            if(choice == 7) {this.printAccessEntity("trainer", "trainers", this.trainerList);}
//            if(choice == 8) {this.printAccessEntity("assignment", "assignments", this.assignmentList);}
            if (choice == 9) {
                check = false;
            }
//            if(choice == 10) {System.out.println("Printing student's enrolled to more than one course: "); this.printStudentsToMoreThanOneCourse(this.studentList);}
//            if(choice == 11) {this.createSyntheticData();}
        }
    }

    public static void studentsCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Create a student."
                    + "\n2. View all students."
                    + "\n3. Edit a student."
                    + "\n4. Delete a student."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.createStudent(sc);}
            if (choice == 2) {PrintUtils.printList(StudentDAO.getAllStudents());}
            if (choice == 3) {School.pickAndEditStudent(sc);}
            if (choice == 4) {School.pickAndDeleteStudent(sc);}
            if (choice == 5) {check = false;}
        }
    }

    public static void coursesCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Create a course."
                    + "\n2. View all courses."
                    + "\n3. Edit a course."
                    + "\n4. Delete a course."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.createCourse(sc);}
            if (choice == 2) {PrintUtils.printList(CourseDAO.getAllCourses());}
            if (choice == 3) {School.pickAndEditCourse(sc);}
            if (choice == 4) {School.pickAndDeleteCourse(sc);}
            if (choice == 5) {check = false;}

        }
    }

    public static void trainersCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Create a trainer."
                    + "\n2. View all trainers."
                    + "\n3. Edit a trainer."
                    + "\n4. Delete a trainer."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.createTrainer(sc);}
            if (choice == 2) {PrintUtils.printList(TrainerDAO.getAllTrainers());}
            if (choice == 3) {School.pickAndEditTrainer(sc);}
            if (choice == 4) {School.pickAndDeleteTrainer(sc);}
            if (choice == 5) {check = false;}

        }
    }

    public static void assignmentsCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Create an assignment."
                    + "\n2. View all assignments."
                    + "\n3. Edit an assignment."
                    + "\n4. Delete an assignment."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.createAssignment(sc);}
            if (choice == 2) {PrintUtils.printList(AssignmentDAO.getAllAssignments());}
            if (choice == 3) {School.pickAndEditAssignment(sc);}
            if (choice == 4) {School.pickAndDeleteAssignment(sc);}
            if (choice == 5) {check = false;}

        }
    }
}
