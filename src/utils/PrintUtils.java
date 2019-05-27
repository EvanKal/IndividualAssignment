/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import dao.CourseDAO;
import model.IndividualAssignment;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import model.AssignmentInCourse;
import model.Course;
import model.StudentInCourse;
import model.TrainerInCourse;

/**
 *
 * @author Los_e
 */
public class PrintUtils {

    public static void printList(ArrayList arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            for (int i = 0; i < arraylist.size(); i++) {
                if (arraylist.get(i) instanceof IndividualAssignment) {
                    System.out.println(">>> " + (i + 1) + ". " + arraylist.get(i).toString());
//            System.out.println(arraylist.get(i).toString());
                } else {
                    System.out.println(">>> " + (i + 1) + ". " + arraylist.get(i).toString());
//            System.out.println(arraylist.get(i).toString());
                }

            }
        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printListCoursesSchedule(ArrayList<Course> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            LocalDate dateprinted = arraylist.get(0).getStartdate();
            LocalDate lastdatetobeprinted = arraylist.get(arraylist.size() - 1).getEnddate().plusDays(1);
            int j = 0;
            System.out.println("\n>>> Schedule for: " + arraylist.get(0).getStream().toUpperCase() + " " + arraylist.get(0).getType().toUpperCase());

            while (dateprinted.isBefore(lastdatetobeprinted)) {
                String output = dateprinted.toString();

                if (DayOfWeek.from(dateprinted).getValue() != 6 && DayOfWeek.from(dateprinted).getValue() != 7) {
                    for (int i = 0; i < arraylist.size(); i++) {

                        if (dateprinted.isBefore(arraylist.get(i).getEnddate().plusDays(1))
                                && dateprinted.isAfter(arraylist.get(i).getStartdate().minusDays(1))) {
                            output = output + "\n*" + arraylist.get(i).getTitle();
                        }
                    }
                } else {
                    output = output + "\n*" + "WEEKEND";
                }
                System.out.println(output);
                j = j + 1;
                dateprinted = dateprinted.plusDays(1);

            }

        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printListCoursesDailyScheduleForStudent(ArrayList<StudentInCourse> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            LocalDate dateprinted = arraylist.get(0).getCourse().getStartdate();
            LocalDate lastdatetobeprinted = arraylist.get(arraylist.size() - 1).getCourse().getEnddate().plusDays(1);
            int j = 0;
            System.out.println("\n>>> Schedule for student with id: " + arraylist.get(0).getStudent().getStudentid());

            while (dateprinted.isBefore(lastdatetobeprinted)) {
                String output = dateprinted.toString();

                if (DayOfWeek.from(dateprinted).getValue() != 6 && DayOfWeek.from(dateprinted).getValue() != 7) {
                    for (int i = 0; i < arraylist.size(); i++) {

                        if (dateprinted.isBefore(arraylist.get(i).getCourse().getEnddate().plusDays(1))
                                && dateprinted.isAfter(arraylist.get(i).getCourse().getStartdate().minusDays(1))) {

                            if (arraylist.get(i).isEnrolled()) {
                                output = output + "\n* Enrolled: " + arraylist.get(i).getCourse().getTitle();
                            } else {
                                output = output + "\n* Not enrolled: " + arraylist.get(i).getCourse().getTitle();

                            }

                        }
                    }
                } else {
                    output = output + "\n*" + "WEEKEND";
                }
                System.out.println(output);
                j = j + 1;
                dateprinted = dateprinted.plusDays(1);

            }

        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printListAssignmentsForStudent(ArrayList<IndividualAssignment> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");

            System.out.println("\n>>> Assignment submission dates for student with id: " + arraylist.get(0).getStudent().getStudentid());

            int courseid = 0;

            for (int i = 0; i < arraylist.size(); i++) {

                if (courseid != arraylist.get(i).getCourseid()) {
                    System.out.println("\nCourse: " + CourseDAO.getCourseById(arraylist.get(i).getCourseid()).getTitle());
                } else {
                }

                if (arraylist.get(i).isSubmitted()) {
                    System.out.println((i+1) + " Submitted: " + arraylist.get(i).getTitle() + " by " + arraylist.get(i).getSubmissiondatetime());
                } else {
                    System.out.println((i + 1) + " Not submitted: " + arraylist.get(i).getTitle() + " by " + arraylist.get(i).getSubmissiondatetime());
                }

                courseid = arraylist.get(i).getCourseid();
            }
        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printEntitiesPerEntityStudentInCourse(ArrayList<StudentInCourse> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            int courseid = 0;

            for (int i = 0; i < arraylist.size(); i++) {

                if (courseid != arraylist.get(i).getCourse().getCourseid()) {
                    System.out.println("\n>>>" + arraylist.get(i).getCourse().toString());
                }

                if (arraylist.get(i).isEnrolled()) {
                    System.out.println("Enrolled " + arraylist.get(i).getStudent().toString());
                } else {
                    System.out.println("Not enrolled " + arraylist.get(i).getStudent().toString());
                }

                courseid = arraylist.get(i).getCourse().getCourseid();
            }
        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printEntitiesPerEntityTrainerInCourse(ArrayList<TrainerInCourse> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            int courseid = 0;

            for (int i = 0; i < arraylist.size(); i++) {

                if (courseid != arraylist.get(i).getCourse().getCourseid()) {
                    System.out.println("\n>>>" + arraylist.get(i).getCourse().toString());
                }

                System.out.println(arraylist.get(i).getTrainer().toString());
                courseid = arraylist.get(i).getCourse().getCourseid();
            }
        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

    public static void printEntitiesPerEntityAssignmentInCourse(ArrayList<AssignmentInCourse> arraylist) {

        if (arraylist.size() > 0) {
            System.out.println("\nPrinting list with " + arraylist.size() + " elements:");
            int courseid = 0;

            for (int i = 0; i < arraylist.size(); i++) {

                if (courseid != arraylist.get(i).getCourse().getCourseid()) {
                    System.out.println("\n>>>" + arraylist.get(i).getCourse().toString());
                }

                System.out.println(arraylist.get(i).getAssignment().toString() + " " + arraylist.get(i).getSubmissiondatetimetoparsedstring());
                courseid = arraylist.get(i).getCourse().getCourseid();
            }
        } else if (arraylist.isEmpty()) {
            System.out.println("Currently no items in list");
        }

    }

}
