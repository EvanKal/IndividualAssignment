/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menus;

import java.util.Scanner;
import utils.InputUtils;

/**
 *
 * @author Los_e
 */
public class HeadmasterMenu {
    
    public static void headmasterMainMenu(Scanner sc) {
        while (1 == 1) {
            System.out.println("\nChoose what you want to do by typing the corresponding number.\n"
                    + "\n1. Create one or more students"
                    + "\n2. Create one or more courses."
                    + "\n3. Create one or more trainers."
                    + "\n4. Create one or more assignments."
                    + "\n5. To view or access students."
                    + "\n6. To view or access  courses."
                    + "\n7. To view or access trainers."
                    + "\n8. To view or access assignments."
                    + "\n9. To view students that need to submit an assignment by a given date."
                    + "\n10. To view students enrolled to more than one courses."
                    + "\n11. To create synthetic data.");

            int choice = InputUtils.inputInt(sc);

            while (choice < 1 || choice > 11) {
                System.out.println("Invalid choice. Please type a number again.");
                choice = InputUtils.inputInt(sc);
            }

//            if(choice == 1) {this.initiateStudentCreation();}
//            if(choice == 2) {this.initiateCourseCreation();}
//            if(choice == 3) {this.initiateTrainerCreation();}
//            if(choice == 4) {this.initiateAssignmentCreation();}
//            if(choice == 5) {this.printAccessEntity("student", "students", this.studentList);}
//            if(choice == 6) {this.printAccessEntity("course", "courses", this.courseList);}
//            if(choice == 7) {this.printAccessEntity("trainer", "trainers", this.trainerList);}
//            if(choice == 8) {this.printAccessEntity("assignment", "assignments", this.assignmentList);}
//            if(choice == 9) {System.out.println("Input date: ");this.printStudentsThatNeedToSubmitThisWeek(this.inputStringDate());}
//            if(choice == 10) {System.out.println("Printing student's enrolled to more than one course: "); this.printStudentsToMoreThanOneCourse(this.studentList);}
//            if(choice == 11) {this.createSyntheticData();}
        }
    }
}
