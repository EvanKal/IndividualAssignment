/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import dao.AssignmentDAO;
import dao.AssignmentInCourseDAO;
import dao.CourseDAO;
import dao.StudentDAO;
import dao.StudentInCourseDAO;
import dao.TrainerDAO;
import dao.TrainerInCourseDAO;
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
            if (choice == 5) {
                HeadmasterMenu.studentsPerCoursesCRUDMenu(sc);
            }
            if (choice == 6) {
                HeadmasterMenu.trainersPerCoursesCRUDMenu(sc);
            }
            if (choice == 7) {
                HeadmasterMenu.assignmentsPerCoursesCRUDMenu(sc);
            }
            if (choice == 8) {
                HeadmasterMenu.schedulePerCoursesCRUDMenu(sc);
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
    
    public static void studentsPerCoursesCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Appoint students to a course."
                    + "\n2. View all students per course."
                    + "\n3. Hard enroll students to appointed course (students are supposed to do so themselves)."
                    + "\n4. Dismiss students from a course."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.appointStudentsToCourse(sc);}
            if (choice == 2) {PrintUtils.printEntitiesPerEntityStudentInCourse(StudentInCourseDAO.getAllStudentsPerCourse());}
            if (choice == 3) {School.enrollStudentsToCourse(sc);}
            if (choice == 4) {School.dismissStudentsFromCourse(sc);}
            if (choice == 5) {check = false;}
        }
    }
    
    public static void trainersPerCoursesCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Appoint trainers to a course."
                    + "\n2. View all trainers per course."
//                    + "\n3. Hard enroll students to appointed course (students are supposed to do so themselves)."
                    + "\n3. Dismiss trainers from a course."
                    + "\n4. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 4) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.appointTrainersToCourse(sc);}
            if (choice == 2) {PrintUtils.printEntitiesPerEntityTrainerInCourse(TrainerInCourseDAO.getAllTrainersPerCourse());}
            if (choice == 3) {School.dismissTrainersFromCourse(sc);}
            if (choice == 4) {check = false;}
}
 }
    
    public static void assignmentsPerCoursesCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Appoint assignments to a course."
                    + "\n2. View all assignments per course."
                    + "\n3. Edit submission date and time of assignment."
                    + "\n4. Dismiss assignments from a course."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice >5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {School.appointAssignmentsToCourse(sc);}
            if (choice == 2) {PrintUtils.printEntitiesPerEntityAssignmentInCourse(AssignmentInCourseDAO.getAllAssignmentsPerCourse());}
            if (choice == 3) {School.editSubmissiondatetimeOfAssignment(sc);}
            if (choice == 4) {School.dismissAssignmentsFromCourse(sc);}
            if (choice == 5) {check = false;}
}
 }
    
    
    public static void schedulePerCoursesCRUDMenu(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. View schedule per stream and type."
                    + "\n2. Edit schedule per stream and type."
                    + "\n3. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice >3) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {HeadmasterMenu.schedulePerCoursesChoose(sc);}
            if (choice == 2) {School.pickAndEditCourseDates(sc);}
            if (choice == 3) {check = false;}
}
 }
    
    public static void schedulePerCoursesChoose(Scanner sc) {

        boolean check = true;

        while (check) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. JAVA - FULL."
                    + "\n2. JAVA - PART."
                    + "\n3. C# - FULL."
                    + "\n4. C# - PART."
                    + "\n5. Back."
            );

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice >5) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

            if (choice == 1) {PrintUtils.printListCoursesScheduleByStreamType(CourseDAO.getAllCoursesOrderedByStartdatePerStreamType("JAVA", "FULL"));}
            if (choice == 2) {PrintUtils.printListCoursesScheduleByStreamType(CourseDAO.getAllCoursesOrderedByStartdatePerStreamType("JAVA", "PART"));}
            if (choice == 3) {PrintUtils.printListCoursesScheduleByStreamType(CourseDAO.getAllCoursesOrderedByStartdatePerStreamType("C#", "FULL"));}
            if (choice == 4) {PrintUtils.printListCoursesScheduleByStreamType(CourseDAO.getAllCoursesOrderedByStartdatePerStreamType("C#", "PART"));}
            if (choice == 5) {check = false;}
}
 }
    
}
